package com.example.theshrubs.plantatree.models;

public class Wishlist {
    private int wishlistID;
    private String productName;
    private int productID;
    private String productDescription;
    private double productCost;
    private int photoID;

    //default constructor allowing Cart to be instantiated with no values
    public Wishlist() {

    }

    //Constructor for creating a NEW empty cart!
    public Wishlist(int wishlistID) {
        setWishlistID(wishlistID);
    }

    public Wishlist(int userID, int productid, String description, String name, double productCost, int photo) {
        setWishlistID(userID);
        setProductID(productid);
        setProductDescription(description);
        setProductName(name);
        setProductCost(productCost);
        setPhotoID(photo);

    }

    public int getWishlistID() {
        return wishlistID;
    }

    public void setWishlistID(int wishlistID) {
        this.wishlistID = wishlistID;
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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public String toString() {
        return "CartID: " + getWishlistID() + " ProdID " + getProductID() + " NAME: " + getProductName() + " COST: " + getProductCost() + "photo" + getPhotoID();


    }
}
