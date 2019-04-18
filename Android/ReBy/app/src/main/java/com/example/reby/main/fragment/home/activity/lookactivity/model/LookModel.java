package com.example.reby.main.fragment.home.activity.lookactivity.model;

import java.io.Serializable;

public class LookModel implements Serializable {
    private int imageId;
    private String lookName;
    private String lookPrice;
    private String viewNumber;

    public LookModel(int imageId, String lookName, String lookPrice, String viewNumber){
        this.imageId = imageId;
        this.lookName = lookName;
        this.lookPrice = lookPrice;
        this.viewNumber = viewNumber;
    }

    public int getImageId() {
        return imageId;
    }

    public String getLookName() {
        return lookName;
    }

    public String getLookPrice() {
        return lookPrice;
    }

    public String getViewNumber() {
        return viewNumber;
    }
}
