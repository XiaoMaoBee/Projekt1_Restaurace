package com.engeto.projekt1.restaurace;

import java.util.ArrayList;
import java.util.List;

public class PhotoList {

    private List<Photo> photoList = new ArrayList<>();


    public void addPhoto(Photo photo) {
        photoList.add(photo);
    }
    public void removePhoto(Photo photo) {
        photoList.remove(photo);
    }

}
