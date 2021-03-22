package com.example.viikko9;

public class FinnkinoTheather {
    private String ID = "";
    private String place = "";
    public FinnkinoTheather(String id, String teatherplace){
        ID = id;
        place = teatherplace;
    }

    public String getID(){
        return ID;
    }
    public String getPlace(){
        return place;
    }
    @Override
    public String toString(){
        return place;
    }
}
