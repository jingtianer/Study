package com.example.reby;

public class MessageModel {
    private int portraitId;
    private String name;
    private String reason;
    private String time;

    public MessageModel(int portraitId, String name, String reason, String time){
        this.portraitId = portraitId;
        this.name = name;
        this.reason = reason;
        this.time = time;
    }

    public int getPortraitId(){
        return portraitId;
    }
    public String getName(){
        return name;
    }
    public String getReason(){
        return reason;
    }
    public String getTime(){
        return time;
    }
}
