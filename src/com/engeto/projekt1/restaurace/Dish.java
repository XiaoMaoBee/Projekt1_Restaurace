package com.engeto.projekt1.restaurace;

import java.math.BigDecimal;

public class Dish {

    private String dishTitle;
    private CategoryDish dishCategory;
    private BigDecimal price;
    private int preparationTime;
    private String dishPhoto;


    public Dish(String title,CategoryDish dishCategory,
                BigDecimal price,int preparationTime,String dishPhoto) {
        this.dishTitle = title;
        this.dishCategory = dishCategory;
        this.price = price;
        this.preparationTime = preparationTime;
        this.dishPhoto = dishPhoto;
    }

    public Dish(String dishTitle) {
    this(dishTitle,CategoryDish.DISH,BigDecimal.ZERO,0,"N/A");
    }

    ///region GETTERS/SETTERS

    public String getDishTitle() {
        return dishTitle;
    }

    public void setDishTitle(String dishTitle) {
        this.dishTitle = dishTitle;
    }

    public CategoryDish getDishCategory() {
        return dishCategory;
    }

    public void setDishCategory(CategoryDish dishCategory) {
        this.dishCategory = dishCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDishPhoto() {
        return dishPhoto;
    }

    public void setDishPhoto(String dishPhoto) {
        this.dishPhoto = dishPhoto;
    }
    ///endregion


    @Override
    public String toString() {
        return "\nTitle: " + dishTitle +
                ", Category: " + dishCategory +
                ", Price: " + price +
                ", PreparationTime: " + preparationTime +
                "min, Photo: " + dishPhoto;}
    }


