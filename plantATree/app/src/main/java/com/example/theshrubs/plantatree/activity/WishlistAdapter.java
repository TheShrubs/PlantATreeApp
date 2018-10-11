package com.example.theshrubs.plantatree.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;
import com.example.theshrubs.plantatree.models.Wishlist;

import java.util.List;

public class WishlistAdapter extends BaseAdapter {

    private List<Wishlist> productList;
    private LayoutInflater layoutInflater;
    private Context context;
    private int currentUSER_ID;

    private static Product currentProduct;

    public WishlistAdapter(Context context, List<Wishlist> products, int id){
        this.context = context;
        this.productList = products;
        this.layoutInflater = LayoutInflater.from(context);
        this.currentUSER_ID = id;
    }


    public class ViewHolder {
        TextView itemName;
        TextView itemDescription;
        TextView itemTotal;
        ImageView itemPhoto;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        currentProduct = null;
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.landing_page_item, null);
            viewHolder = new ViewHolder();

            viewHolder.itemName = (TextView) convertView.findViewById(R.id.mainTitle);
            viewHolder.itemTotal = (TextView) convertView.findViewById(R.id.mainPrice);
            viewHolder.itemPhoto = (ImageView) convertView.findViewById(R.id.mainImage);
            viewHolder.itemDescription = (TextView) convertView.findViewById(R.id.mainDescription);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        final Wishlist cartItem = this.productList.get(position);
        viewHolder.itemName.setText(cartItem.getProductName());
        viewHolder.itemTotal.setText("$" + cartItem.getProductCost());
        viewHolder.itemDescription.setText(cartItem.getProductDescription());
        viewHolder.itemPhoto.setImageResource(cartItem.getPhotoID());
        convertView.setOnClickListener(new View.OnClickListener() {
            int newID = productList.get(position).getProductID();
            @Override
            public void onClick(View v) {
                int newID = productList.get(position).getProductID();
                Intent intent = new Intent(context, ViewItemActivity.class);
                intent.putExtra("TREE_ID", newID);
                intent.putExtra("USER_ID", currentUSER_ID);

                context.startActivity(intent);
            }
        });

        return convertView;
    }


    public static Product getProduct(){
        return currentProduct;
    }
}



