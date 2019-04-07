package com.example.reby;

public class SaleModel {
    private int imgLadyId;
    private String saleName;
    private String salePrice;
    private String deadLine;
    private String participateNumber;
    public SaleModel(int imgLadyId, String saleName, String salePrice,String deadLine,String participateNumber){
        this.imgLadyId = imgLadyId;
        this.saleName = saleName;
        this.salePrice = salePrice;
        this.deadLine = deadLine;
        this.participateNumber = participateNumber;
    }

    public int getImgLadyId(){
        return imgLadyId;
    }
    public String getSaleName(){
        return saleName;
    }
    public String getSalePrice(){
        return salePrice;
    }
    public String getDeadLine(){
        return deadLine;
    }
    public String getParticipateNumber(){
        return participateNumber;
    }

}
