package com.example.theshrubs.plantatree.activity;

import android.content.Context;
import android.text.TextWatcher;
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

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends BaseAdapter {
    private List<ShoppingCart> cartObejctsList;
    private LayoutInflater layoutInflater;
    private Context context;
    private int currentUSER_ID;

    public ShoppingCartAdapter(Context context, List<Object> objList, int id) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.currentUSER_ID = id;

        this.cartObejctsList = new ArrayList<>();
        for (int i = 0; i < objList.size(); i++) {
            cartObejctsList.add((ShoppingCart) objList.get(i));
        }
    }

    private class ViewHolder {
        TextView itemName;
        TextView itemTotal;
        EditText itemQuantity;
        ImageView itemPhoto;
    }

    @Override
    public int getCount() {
        return cartObejctsList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartObejctsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.shopping_cart_item, null);
            viewHolder = new ViewHolder();
            viewHolder.itemName = (TextView) convertView.findViewById(R.id.shopItemName);
            viewHolder.itemTotal = (TextView) convertView.findViewById(R.id.shopItemTotal);
            viewHolder.itemPhoto = (ImageView) convertView.findViewById(R.id.shopItemPhoto);
            viewHolder.itemQuantity = (EditText) convertView.findViewById(R.id.shopItemQuantity);
//            viewHolder.itemQuantity.addTextChangedListener(new TextWatcher() {
//                @Override
//                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                }
//
//                @Override
//                public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                    String stringValue = viewHolder.itemQuantity.getText().toString();
//                    if (!(stringValue.equals(""))) {
//                        int quantity = Integer.parseInt(stringValue);
//                        //   cartObejctsList.get(position).getProductQuantity();
//                        //   update(viewHolder, cartObejctsList.get(position));
//                        //   notifyDataSetInvalidated();
////                        return;
//                    }
//                }
//
//                @Override
//                public void afterTextChanged(Editable s) {
//                }
//            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Object obj = cartObejctsList.get(position);
        ShoppingCart cartObject = (ShoppingCart) obj;
        viewHolder.itemName.setText(cartObject.getProductName());
        viewHolder.itemTotal.setText("$" + cartObject.getTotalCost());
        viewHolder.itemQuantity.setText(String.valueOf(cartObject.getProductQuantity()));
        viewHolder.itemPhoto.setImageResource(cartObject.getPhotoID());
        // updateDetails(viewHolder, cartObejctsList, position);
        return convertView;
    }

    public void updateDetails(ViewHolder v, List<ShoppingCart> cartList, int position) {

        Object obj = cartList.get(position);
        ShoppingCart cartObject = (ShoppingCart) obj;
        v.itemName.setText(cartObject.getProductName());
        v.itemTotal.setText("$" + cartObject.getTotalCost());
        v.itemQuantity.setText(String.valueOf(cartObject.getProductQuantity()));
        v.itemPhoto.setImageResource(cartObject.getPhotoID());
    }

    public void update(ViewHolder v, ShoppingCart cartObject){
        v.itemName.setText(cartObject.getProductName());
        v.itemTotal.setText("$" + cartObject.getTotalCost());
        v.itemQuantity.setText(String.valueOf(cartObject.getProductQuantity()));
        v.itemPhoto.setImageResource(cartObject.getPhotoID());
    }

}
