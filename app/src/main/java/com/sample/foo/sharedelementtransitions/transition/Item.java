package com.sample.foo.sharedelementtransitions.transition;

public class Item {

    String id;
    String name;
    String image;
    String testvariable;

    public Item(String id,String name,String image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTestvariable() {
        return testvariable;
    }

    public void setTestvariable(String testvariable) {
        this.testvariable = testvariable;
    }
}
