package com.example.reby;

public class GoodsModel {
    private int imageId;
    private String goodsIntroduction;
    public GoodsModel( int imageId,String goodsIntroduction){
        this.imageId = imageId ;
        this.goodsIntroduction = goodsIntroduction;
    }
    public int getImageId(){
        return imageId;
    }
    public String getIntroduction(){
        return goodsIntroduction;
    }

}
