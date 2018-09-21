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
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LandingPageAdapter extends BaseAdapter {

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<Tree> treeList;
    ArrayList<Tree> arrayList;

    //constructor
    public LandingPageAdapter(Context context, List<Tree> modelList) {
        mContext = context;
        this.treeList = modelList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Tree>();
        this.arrayList.addAll(treeList);
    }

    public class ViewHolder{
        TextView mTitleTV, mPriceTV;
        ImageView mIconIV;
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
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.landing_page_item, null);
            holder.mTitleTV = convertView.findViewById(R.id.mainTitle);
            holder.mPriceTV = convertView.findViewById(R.id.mainPrice);
            holder.mIconIV = convertView.findViewById(R.id.mainImage);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        //set the results into textview
        holder.mTitleTV.setText(treeList.get(position).getTreeName());
        holder.mPriceTV.setText("$" + treeList.get(position).getPrice());
        //set the result in imageview
        holder.mIconIV.setImageResource(treeList.get(position).getPhotoID());

        //listview item clicks
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int newID = treeList.get(position).getTreeID();
                    Intent intent = new Intent(mContext, ViewItemActivity.class);
                    intent.putExtra("TREE_ID", newID);;

                    mContext.startActivity(intent);
            }
        });

        return convertView;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        treeList.clear();
        if(charText.length()==0){
            treeList.addAll(arrayList);
        } else {
            for(Tree model: arrayList){
                if(model.getTreeName().toLowerCase(Locale.getDefault()).contains(charText)){
                    treeList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
