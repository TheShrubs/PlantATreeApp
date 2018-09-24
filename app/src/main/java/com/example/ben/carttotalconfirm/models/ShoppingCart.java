package com.example.ben.carttotalconfirm.models;

public class ShoppingCart {
    private int cartID;
    private int productID;
    private int userID;
    private double productCost;
    private double deliveryCost;
    private double totalCost;

    public ShoppingCart(int cartid, int productid, int user, double productCost, double deliveryCost, double totalCost){
        setCartID(cartid);
        setProductID(productid);
        setUserID(user);
        setProductCost(productCost);
        setDeliveryCost(deliveryCost);
        setTotalCost(totalCost);
    }

    public int getCartID() {
        return cartID;
    }

    public void setCartID(int cartID) {
        this.cartID = cartID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}

