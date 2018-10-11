package com.example.theshrubs.plantatree.models;

public class Wishlist {
    private int cartID;
    private String productName;
    private int productID;
    private double productCost;
    private double deliveryCost;
    private int productQuantity;
    private double totalCost;
    private int photoID;

    //default constructor allowing Cart to be instantiated with no values
    public Wishlist(){

    }

    //Constructor for creating a NEW empty cart!
    public Wishlist(int cartID){
        setCartID(cartID);
    }

    public Wishlist(int userID, int productid, String name, double productCost, double deliveryCost, int quantity, double totalCost, int photo){
        setCartID(userID);
        setProductID(productid);
        setProductName(name);
        setProductCost(productCost);
        setDeliveryCost(deliveryCost);
        setProductQuantity(quantity);
        setTotalCost(totalCost);
        setPhotoID(photo);

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public String toString(){
        return "CartID: " + getCartID()+ " ProdID " + getProductID() + " NAME: " + getProductName() + " COST: " + getProductCost() + "DELIVERY: " + getDeliveryCost() + " QUANTITY " + getProductQuantity() + " total: " + getTotalCost() + "photo" + getPhotoID();


    }
}
