package com.engeto.projekt1.restaurace;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Menu{

    public Menu() {
    }

    public void writeMenuInFile(List<Dish> list, String filename) throws OrdersException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
            for (Dish dish : list) {
                String delimiter = ";";
                String record = dish.getDishTitle() + delimiter
                        + dish.getDishCategory() + delimiter
                        + dish.getPrice() + delimiter
                        + dish.getPreparationTime() + delimiter
                        + dish.getDishPhoto();
                System.out.println(record);
                writer.println(record);
            }
        } catch (IOException e) {
            throw new OrdersException(e.getLocalizedMessage());
        }
    }

    public void readMenuFromFile(String filename, List<Dish> menuList, String delimiter) throws OrdersException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split(delimiter);
                Dish dish = new Dish(items[0],
                        CategoryDish.valueOf(items[1]),
                        new BigDecimal(items[2]),
                        Integer.parseInt(items[3]),
                        items[4]);

                menuList.add(dish);
            }
            System.out.println(menuList);
        } catch (FileNotFoundException e) {
            throw new OrdersException("File is broken or does not exists: file: "
                    + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            throw new OrdersException("Wrong datatype of value" + e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrdersException("Missing parameter - " + e.getLocalizedMessage());
        }
    }


    public void checkDishAvailability(List<Dish> menu,Dish dish) {
        if (!menu.contains(dish)) {
            System.out.println("Dish: " + dish.getDishTitle() + " N/A (not in menu)");
        } else {
            System.out.println("Dish: " + dish.getDishTitle() + " is available.");
        }
    }
}

