package com.example.project;

public class responsemodel {
    String id,name,desig,image;

    public responsemodel() {
    }

    public responsemodel(String id,String name,String desig,String image) {
        this.id = id;
        this.image=image;
        this.desig=desig;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
