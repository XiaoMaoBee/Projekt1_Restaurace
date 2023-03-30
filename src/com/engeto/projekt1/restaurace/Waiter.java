package com.engeto.projekt1.restaurace;

public class Waiter {

    private String name;
    private int id;

    public Waiter(String name,int id) {
        this.name = name;
        this.id = id;
    }
    public Waiter(String name) {
        this(name,0);
    }

    ///region GETTERS/SETTERS



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    ///endregion


    @Override
    public String toString() {
        return name;}
    }

