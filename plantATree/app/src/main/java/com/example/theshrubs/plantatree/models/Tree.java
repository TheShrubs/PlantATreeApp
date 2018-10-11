package com.example.theshrubs.plantatree.models;

public class Tree {

    private int treeID;
    private String treeName;
    private String treeDescription;
    private Category category;
    private double price;
    private int photoID;
    private int photoThumb;
    private int photoDrag;
    private double shippingCost;
    private MaxHeight maxHeight;
    private SoilDrainage soilDrainage;
    private SunExposure sunExposure;
    private GrowthRate growthRate;
    private Maintenance maintenanceReq;


    //default constructor so class can be instantiated without parameters
    public Tree() {

    }

    public Tree(int treeID, String name, double price, int photoID){
        this.setTreeID(treeID);
        this.setTreeName(name);
        this.setPrice(price);
        this.setPhotoID(photoID);

    }

    public Tree(int id, String name, String description, String category, double price, int photo, int photoThumb, int photoDrag, double shippingCost, String maxHeight, String soilDrainage, String sunExposure, String growthRate, String maintenanceReq) {
        this.setTreeID(id);
        this.setTreeName(name);
        this.setTreeDescription(description);
        this.setCategory(category);
        this.setPrice(price);
        this.setPhotoID(photo);
        this.setPhotoThumb(photoThumb);
        this.setPhotoDrag(photoDrag);
        this.setShippingCost(shippingCost);
        this.setMaxHeight(maxHeight);
        this.setSoilDrainage(soilDrainage);
        this.setSunExposure(sunExposure);
        this.setGrowthRate(growthRate);
        this.setMaintenanceReq(maintenanceReq);
    }

    public int getTreeID() {
        return treeID;
    }

    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    public void setTreeDescription(String treeDescription) {
        this.treeDescription = treeDescription;
    }

    public String getTreeDescription() {
        return treeDescription;
    }

    public String getCategory() {
        return category.toString();
    }

    public void setCategory(String category) {
        switch (category) {
            case "Flowering":
                this.category = Category.FLORA;
                break;
            case "Non-flowering":
                this.category = Category.FAUNA;
                break;
            default:
                this.category = Category.FLORA;
                break;
        }

    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public int getPhotoThumb() {
        return photoThumb;
    }

    public void setPhotoThumb(int photoThumb) {
        this.photoThumb = photoThumb;
    }

    public int getPhotoDrag() {
        return photoDrag;
    }

    public void setPhotoDrag(int photoDrag) {
        this.photoDrag = photoDrag;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getMaxHeight() {
        return maxHeight.toString();
    }

    public void setMaxHeight(String maxHeight) {
        switch (maxHeight) {
            case "High":
                this.maxHeight = MaxHeight.HIGH;
                break;
            case "Medium":
                this.maxHeight = MaxHeight.MEDIUM;
                break;
            case "Low":
                this.maxHeight = MaxHeight.LOW;
                break;
            default:
                this.maxHeight = MaxHeight.MEDIUM;

        }

    }

    public String getSoilDrainage() {
        return soilDrainage.toString();
    }

    public void setSoilDrainage(String soilDrainage) {
        switch (soilDrainage) {
            case "High":
                this.soilDrainage = SoilDrainage.HIGH;
                break;
            case "Medium":
                this.soilDrainage = SoilDrainage.MEDIUM;
                break;
            case "Low":
                this.soilDrainage = SoilDrainage.LOW;
                break;
            default:
                this.soilDrainage = SoilDrainage.MEDIUM;

        }
    }

    public String getSunExposure() {
        return sunExposure.toString();
    }

    public void setSunExposure(String sunExposure) {

        switch (sunExposure) {
            case "High":
                this.sunExposure = SunExposure.HIGH;
                break;
            case "Medium":
                this.sunExposure = SunExposure.MEDIUM;
                break;
            case "Low":
                this.sunExposure = SunExposure.LOW;
                break;
            default:
                this.sunExposure = SunExposure.MEDIUM;
        }
    }

    public String getGrowthRate() {
        return growthRate.toString();
    }

    public void setGrowthRate(String growthRate) {
        switch (growthRate) {
            case "High":
                this.growthRate = GrowthRate.HIGH;
                break;
            case "Medium":
                this.growthRate = GrowthRate.MEDIUM;
                break;
            case "Low":
                this.growthRate = GrowthRate.LOW;
                break;
            default:
                this.growthRate = GrowthRate.MEDIUM;
        }
    }

    public String getMaintenanceReq() {
        return maintenanceReq.toString();
    }

    public void setMaintenanceReq(String maintenanceReq) {
        switch (maintenanceReq) {
            case "High":
                this.maintenanceReq = Maintenance.HIGH;
                break;
            case "Medium":
                this.maintenanceReq = Maintenance.MEDIUM;
                break;
            case "Low":
                this.maintenanceReq = Maintenance.LOW;
                break;
            default:
                this.maintenanceReq = Maintenance.MEDIUM;
        }
    }


    public String getProduct() {
        return "Name: " + getTreeName() +
                "\n" + getTreeDescription() +
                "\n " + getPrice();
    }

    public String toString() {

        return getTreeID() + getTreeName() + getTreeDescription() + getCategory() + getPrice() + getPhotoID() + getPhotoThumb() + getPhotoDrag() + getMaxHeight() + getSoilDrainage() + getSunExposure() + getGrowthRate() + getMaintenanceReq();
    }
}
