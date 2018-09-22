package com.example.theshrubs.plantatree.models;

public class Product {
    private int productID;
    private int quantity;
    private double productPrice;
    private double productTotal;
    private String productName;
    private int photoID;



    private double shipping;

    public Product(int productID, String prodName, double productPrice, double shipping, double productTotal, int photo){
        this.productID = productID;
        this.productName = prodName;
        this.productPrice = productPrice;
        this.shipping = shipping;
        this.productTotal = productTotal;
        this.photoID = photo;
    }

    public void setProductID(int productID){
        this.productID = productID;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setProductPrice(double productPrice){
        this.productPrice = productPrice;
    }

    public void setProductTotal(double productTotal){
        this.productTotal = productTotal;
    }

    public int getProductID(){
        return this.productID;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public double getProductPrice(){
        return this.productPrice;
    }

    public double getProductTotal(){
        return this.productTotal;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(double shipping) {
        this.shipping = shipping;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }




    public String toString(){
        return  " " + getProductID() + " " + getQuantity() + " " + getProductPrice() + " " + getProductTotal();
    }

    public String getProduct(){
        return "";
    }
}
