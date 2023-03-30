package com.engeto.projekt1.restaurace;

public class Photo {

    private String name;
    private String url;


    public Photo(String name) {
        this.name = name;
        this.url = "www.restaurant.cz/dish/picture/" + name;
    }

    ///region GETTERS/SETTERS


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    ///endregion
}
