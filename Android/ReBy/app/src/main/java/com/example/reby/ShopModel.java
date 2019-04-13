package com.example.reby;

public class ShopModel {
    private int shopImageId;
    private String shopName;
    private String shopPrice;
    private String shopView;
    private String shopIsOn;
    public ShopModel(int shopImageId, String shopName, String shopPrice,String shopView,String shopIsOn){
        this.shopName = shopName;
        this.shopImageId = shopImageId;
        this.shopPrice = shopPrice;
        this.shopView = shopView;
        this.shopIsOn = shopIsOn;
    }
    public  int getShopImageId(){
        return shopImageId;
    }
    public String getShopName(){
        return shopName;
    }

    public String getShopPrice() {
        return shopPrice;
    }

    public String getShopView() {
        return shopView;
    }

    public String getShopIsOn() {
        return shopIsOn;
    }
}
