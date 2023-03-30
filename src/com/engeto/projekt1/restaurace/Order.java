package com.engeto.projekt1.restaurace;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class Order implements Comparable<Order>{
    private static int nextId = 1;
    private int orderId;
    private int tableNum;
    private LocalDate date = LocalDate.now();
    private LocalTime orderTime;
    private Dish orderedDish;
    private int numOfDishes;
    private Waiter waiter;
    private LocalTime fulfilmentTime;
    private String note;



    public Order(int orderId,int tableNum,LocalTime orderTime, Dish orderedDish,
                 int numOfDishes, Waiter waiter, LocalTime fulfilmentTime, String note) {
        this.orderId = orderId;
        this.tableNum = tableNum;
        this.orderTime = orderTime;
        this.orderedDish = orderedDish;
        this.numOfDishes = numOfDishes;
        this.waiter = waiter;
        this.fulfilmentTime = fulfilmentTime;
        this.note = note;
    }
    public Order(int tableNum, LocalTime orderTime, Dish orderedDish,
                 int numOfDishes, Waiter waiter, LocalTime fulfilmentTime, String note) {
        this(nextId++,tableNum,orderTime,orderedDish,numOfDishes,waiter,fulfilmentTime,note);
    }
    public Order(int tableNum, LocalTime orderTime, Dish orderedDish,
                 int numOfDishes, Waiter waiter, String note) {
        this(tableNum,orderTime,orderedDish,numOfDishes,waiter,LocalTime.of(0,0),note);
    }


    ///region GETTERS/SETTERS


    public int getNumOfDishes() {
        return numOfDishes;
    }

    public void setNumOfDishes(int numOfDishes) {
        this.numOfDishes = numOfDishes;
    }

    public Dish getOrderedDish() {
        return orderedDish;
    }

    public void setOrderedDish(Dish orderedDish) {
        this.orderedDish = orderedDish;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalTime getFulfilmentTime() {
        return fulfilmentTime;
    }

    public void setFulfilmentTime(LocalTime fulfilmentTime) {
        this.fulfilmentTime = fulfilmentTime;
    }

    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    ///endregion

    public void setNowTime() {setFulfilmentTime(LocalTime.now());}


    @Override
    public int compareTo(Order o) {
        return getWaiter().getName().compareTo(o.getWaiter().getName());
    }

    public BigDecimal getSumOfDishesPrice() {
        return getOrderedDish().getPrice().multiply(BigDecimal.valueOf(getNumOfDishes()));
    }

    @Override
    public String toString() {
        return "\n---\nOrderId: " + orderId +
                "\nTable: " + tableNum +
                ", Date: " + date +
                ", OrderTime: " + orderTime +
                "\nOrderedDish: " + orderedDish +
                "\nNumOfDishes: " + numOfDishes +
                "\nWaiter: " + waiter +
                "\nFulfilmentTime: " + fulfilmentTime +
                "\nNote: " + note;}
    }

