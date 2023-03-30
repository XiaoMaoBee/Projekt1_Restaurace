package com.engeto.projekt1.restaurace;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class DishList {

    public void writeDishListInFile(List<Dish> list, String filename) throws OrdersException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : list) {
                String delimiter = ";";
                String record = dish.getDishTitle() + delimiter
                        + dish.getDishCategory() + delimiter
                        + dish.getPrice() + delimiter
                        + dish.getPreparationTime() + delimiter
                        + dish.getDishPhoto();
                writer.println(record);
            }
        } catch (IOException e) {
            throw new OrdersException(e.getLocalizedMessage());
        }
    }

    public void readDishListFromFile(String filename, String delimiter) throws OrdersException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split(";");
                Dish dish = new Dish(items[0],
                        CategoryDish.valueOf(items[1]),
                        new BigDecimal(items[2]),
                        Integer.parseInt(items[3]),
                        items[4]);
            }
        } catch (FileNotFoundException e) {
            throw new OrdersException("File is broken or does not exists: file: "
                    + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            throw new OrdersException("Wrong datatype of value - " + e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrdersException("Missing parameter - " + e.getLocalizedMessage());

//    //region READ/WRITE
//    public void readDishListFromFile(String fileName) throws OrdersException {
//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String[] items = line.split(";");
//                Dish dish = new Dish(items[0], CategoryDish.valueOf(items[1]),
//                        new BigDecimal(items[2]),
//                        Integer.parseInt(items[3]),
//                        items[4]);
//            }
//        } catch (FileNotFoundException e) {
//            throw new OrdersException(e.getLocalizedMessage());
//        }
//    }
//
//    public void writeDishIntoFile(String fileName) throws OrdersException {
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
//            for (Dish dish : dishList) {
//                String delimiter = ";";
//                String record = "Dish Title: " + dish.getDishTitle() + delimiter
//                        + "Dish Category: " + dish.getDishCategory() + delimiter
//                        + "Price: " + dish.getPrice() + delimiter
//                        + "Preparation Time: " + dish.getPreparationTime() + delimiter
//                        + "Dish Photo: " + dish.getDishPhoto();
//                writer.println(record);
//            }
//        } catch (IOException e) {
//            throw new OrdersException(e.getLocalizedMessage());
//        }
//    }

            //endregion


        }
    }
}
