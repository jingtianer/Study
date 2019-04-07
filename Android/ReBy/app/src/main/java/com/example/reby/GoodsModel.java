package com.example.reby;

public class GoodsModel {
    private int imageId;
    private String goodsPrice;
    private String introduction;
    private int img_saleId;
    private int img_thingId;
    private int img_notalkId;

    public GoodsModel( int imageId,String goodsPrice, String introduction, int img_saleId, int img_thingId, int img_notalkId){
        this.imageId = imageId ;
        this.goodsPrice = goodsPrice;
        this.introduction = introduction;
        this.img_saleId = img_saleId;
        this.img_thingId = img_thingId;
        this.img_notalkId = img_notalkId;
    }

    public int getImageId(){

        return imageId;
    }
    public String getGoodsPrice(){
        return goodsPrice;
    }
   public String getIntroduction(){
        return introduction;
    }
    public int getImg_saleId(){
        return img_saleId;
    }
    public int getImg_thingId(){
        return img_thingId;
    }
    public int getImg_notalkId(){
        return img_notalkId;
    }

}
