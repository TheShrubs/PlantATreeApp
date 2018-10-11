package com.example.theshrubs.plantatree.activity;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.models.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartAdapter extends BaseAdapter {
    private List<ShoppingCart> cartObejctsList;
    private LayoutInflater layoutInflater;
    private ShoppingCartActivity activity;
    private Context context;
    private int currentUSER_ID;

    public ShoppingCartAdapter(Context context, List<Object> objList, int id) {
        this.activity = (ShoppingCartActivity) context;
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
            viewHolder.itemQuantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                }

                @Override
                public void afterTextChanged(Editable s) {
                    String stringValue = viewHolder.itemQuantity.getText().toString();
                    if (!(stringValue.equals(""))) {

                        int quantity = Integer.parseInt(stringValue);

                        if (quantity > 0) {
                            int oldQuantity = cartObejctsList.get(position).getProductQuantity();
                            System.out.println("New quantity is " + quantity + " for item " + cartObejctsList.get(position).getProductName());
                            System.out.println("Old quantity is " + oldQuantity + cartObejctsList.get(position).getProductName());

                            double newTotal = cartObejctsList.get(position).getProductCost() * quantity;
                            viewHolder.itemTotal.setText("$ " + newTotal);
                            cartObejctsList.get(position).setProductQuantity(quantity);
                            cartObejctsList.get(position).setTotalCost(newTotal);
                            activity.reCalculation(cartObejctsList);

                        }


                        if (quantity == 0) {
                            Toast.makeText(activity, "Item successfully removed from cart", Toast.LENGTH_SHORT).show();
                            activity.refreshView(cartObejctsList.get(position).getProductID(), cartObejctsList.get(position).getCartID());
                        }


                    }

                }

            });
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Object obj = cartObejctsList.get(position);
        ShoppingCart cartObject = (ShoppingCart) obj;
        viewHolder.itemName.setText(cartObject.getProductName());
        viewHolder.itemTotal.setText("$ " + cartObject.getTotalCost());
        viewHolder.itemQuantity.setText(String.valueOf(cartObject.getProductQuantity()));
        viewHolder.itemPhoto.setImageResource(cartObject.getPhotoID());
        return convertView;
    }

}
