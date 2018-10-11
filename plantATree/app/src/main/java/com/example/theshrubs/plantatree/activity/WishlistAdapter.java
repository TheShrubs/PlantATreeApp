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

    public WishlistAdapter(Context context, List<Wishlist> products, int id){//}, DatabaseHelper databaseHelper){
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

    public View getView(final int position, View convertView, ViewGroup parent) {
        currentProduct = null;
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shopping_cart_item, null);
            viewHolder = new ViewHolder();

            viewHolder.itemName = (TextView) convertView.findViewById(R.id.shopItemName);
            viewHolder.itemTotal = (TextView) convertView.findViewById(R.id.shopItemTotal);
            viewHolder.itemQuantity = (EditText) convertView.findViewById(R.id.shopItemQuantity);
            viewHolder.itemPhoto = (ImageView) convertView.findViewById(R.id.shopItemPhoto);
            //viewHolder.removeItem = (Button) convertView.findViewById(R.id.removeItem);


            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        final Wishlist cartItem = this.productList.get(position);
        viewHolder.itemName.setText(cartItem.getProductName());
        viewHolder.itemTotal.setText("$" + cartItem.getTotalCost());
        viewHolder.itemQuantity.setText(String.valueOf(cartItem.getProductQuantity()));
        viewHolder.itemPhoto.setImageResource(cartItem.getPhotoID());
        //listview item clicks
        convertView.setOnClickListener(new View.OnClickListener() {
            int newID = productList.get(position).getProductID();
            @Override
            public void onClick(View v) {
                final Wishlist cartItem = productList.get(position);
                currentProduct = new Product(cartItem.getProductID(),cartItem.getProductName(),cartItem.getProductCost(),cartItem.getDeliveryCost(),cartItem.getTotalCost(),cartItem.getPhotoID());

                Intent intent = new Intent(context, AddItemToCartActivity.class);
                intent.putExtra("TREE_ID", cartItem.getProductID());
                intent.putExtra("USER_ID", currentUSER_ID);
                intent.putExtra("PAGE_ID","Wishlist");

                context.startActivity(intent);
            }
        });

        return convertView;
    }


    public static Product getProduct(){
        return currentProduct;
    }
}



