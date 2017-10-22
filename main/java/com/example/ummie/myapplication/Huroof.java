package com.example.ummie.myapplication;

/**
 * Created by lenovo on 18-Oct-17.
 */

public class Huroof {
    private String name;
    private int numOfHuroof;
    private int thumbnail;

    public Huroof() {
    }

    public Huroof(String name,int numOfHuroof,  int thumbnail) {
        this.name = name;
        this.numOfHuroof = numOfHuroof;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfHuroof()
    {
        return numOfHuroof;
    }

    public void setNumOfHuroof(int numOfHuroof)
    {
        this.numOfHuroof = numOfHuroof;
    }


    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
