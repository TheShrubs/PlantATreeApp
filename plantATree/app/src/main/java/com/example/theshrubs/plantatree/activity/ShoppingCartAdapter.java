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
//    private DatabaseHelper databaseHelper;

    public ShoppingCartAdapter(Context context, List<ShoppingCart> products){//}, DatabaseHelper databaseHelper){
        this.context = context;
        this.productList = products;
        this.layoutInflater = LayoutInflater.from(context);
//        this.databaseHelper = new DatabaseHelper(context);
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

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        return null;
//    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.shopping_cart_item, null);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) view.findViewById(R.id.shopItemName);
            viewHolder.itemShipping = (TextView) view.findViewById(R.id.shopShipping);
            viewHolder.itemTotal = (TextView) view.findViewById(R.id.shopItemTotal);
            viewHolder.itemQuantity = (EditText) view.findViewById(R.id.shopItemQuantity);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        ShoppingCart cartItem = this.productList.get(position);
//        Tree treeItem
        viewHolder.itemName.setText(cartItem.getProductName());
        viewHolder.itemTotal.setText("$" + cartItem.getTotalCost());
        viewHolder.itemQuantity.setText(String.valueOf(cartItem.getProductQuantity()));
        viewHolder.itemShipping.setText("Shipping: $" +cartItem.getDeliveryCost());
//        view.item

        System.out.println("fdjkalf;djkalf;jdakal;lfjdka;fjdkal;fjdkla;fjdkala;jfdkla;jfkdla;");
        return view;
    }

    static class ViewHolder {
        TextView itemName;
        TextView itemShipping;
        TextView itemTotal;
        EditText itemQuantity;
    }

}
