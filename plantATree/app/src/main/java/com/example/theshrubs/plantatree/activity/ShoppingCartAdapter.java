package com.example.theshrubs.plantatree.activity;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.Product;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.List;

public class ShoppingCartAdapter extends BaseAdapter {

    private List<ShoppingCart> productList;
    private LayoutInflater layoutInflater;
    private Context context;
    private int currentUSER_ID;
//    private DatabaseHelper databaseHelper;

    public ShoppingCartAdapter(Context context, List<ShoppingCart> products, int id){//}, DatabaseHelper databaseHelper){
        this.context = context;
        this.productList = products;
        this.layoutInflater = LayoutInflater.from(context);
        this.currentUSER_ID = id;
//        this.databaseHelper = new DatabaseHelper(context);
    }

    public class ViewHolder {
        TextView itemName;
//        TextView itemShipping;
        TextView itemTotal;
        EditText itemQuantity;
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

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shopping_cart_item, null);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.shopItemName);
            viewHolder.itemTotal = (TextView) convertView.findViewById(R.id.shopItemTotal);
            viewHolder.itemQuantity = (EditText) convertView.findViewById(R.id.shopItemQuantity);
            viewHolder.itemPhoto = (ImageView) convertView.findViewById(R.id.shopItemPhoto);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ShoppingCart cartItem = this.productList.get(position);
        viewHolder.itemName.setText(cartItem.getProductName());
        viewHolder.itemTotal.setText("$" + cartItem.getTotalCost());
        viewHolder.itemQuantity.setText(String.valueOf(cartItem.getProductQuantity()));
        viewHolder.itemPhoto.setImageResource(cartItem.getPhotoID());
        return convertView;
    }



}
