package com.example.theshrubs.plantatree.activity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;
import java.util.List;

public class LandingPageAdapter extends BaseAdapter {


    private Context mContext;
    private LayoutInflater inflater;
    private List<Tree> treeList;
    private int currentUserID;
    private List<Tree> searchList;
    private List<Tree> fullList;

    //constructor
    public LandingPageAdapter(Context context, List<Tree> modelList, int id) {
        mContext = context;
        this.treeList = modelList;
        this.currentUserID = id;
        inflater = LayoutInflater.from(mContext);
    }

    public class ViewHolder {
        TextView treeTitle;
        TextView treePrice;
        TextView treeDescription;
        ImageView treePhoto;
    }

    @Override
    public int getCount() {
        return treeList.size();
    }

    @Override
    public Object getItem(int position) {
        return treeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.landing_page_item, null);
            holder.treeTitle = convertView.findViewById(R.id.mainTitle);
            holder.treeDescription = convertView.findViewById(R.id.mainDescription);
            holder.treePrice = convertView.findViewById(R.id.mainPrice);
            holder.treePhoto = convertView.findViewById(R.id.mainImage);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.treeTitle.setText(treeList.get(position).getTreeName());
        holder.treeDescription.setText(treeList.get(position).getTreeDescription());
        holder.treePrice.setText("$ " + treeList.get(position).getPrice());
        holder.treePhoto.setImageResource(treeList.get(position).getPhotoID());

        //listview item clicks
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int newID = treeList.get(position).getTreeID();
                Intent intent = new Intent(mContext, ViewItemActivity.class);
                intent.putExtra("TREE_ID", newID);
                intent.putExtra("USER_ID", currentUserID);

                mContext.startActivity(intent);
            }
        });

        fullList = new ArrayList<>();

        return convertView;
    }


    public void filter(String charText, DatabaseHelper db) {
        charText = charText.toLowerCase();
        searchList = new ArrayList<>();
        searchList.addAll(treeList);
        if (charText.length() == 0) {
            treeList.clear();
            List<Object> obj = db.loadAllContents(1, "Landing");
            for (int i = 0; i < obj.size(); i++) {
                Tree tree = (Tree) obj.get(i);
                fullList.add(tree);
            }
            treeList.addAll(fullList);
        } else {
            treeList.clear();
            for (Tree tree : searchList) {
                if (tree.getTreeName().toLowerCase().contains(charText)) {
                    treeList.add(tree);
                }
            }
        }
        notifyDataSetChanged();
    }
}
