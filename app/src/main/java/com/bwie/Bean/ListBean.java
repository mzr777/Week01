package com.bwie.Bean;

/**
 * Created by 董绍华 on 2017/9/17.
 */

public class ListBean {

     int img;
     String name;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ListBean(int img, String name) {
        this.img = img;
        this.name = name;
    }
}
