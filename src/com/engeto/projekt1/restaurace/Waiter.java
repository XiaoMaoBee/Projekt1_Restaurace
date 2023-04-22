package com.engeto.projekt1.restaurace;

public class Waiter {

    private static int nextId = 1;
    private int id;
    private String name;


    public Waiter(String name) {
        this.name = name;
        this.id = nextId++;
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

