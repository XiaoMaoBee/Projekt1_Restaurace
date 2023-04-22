package com.engeto.projekt1.restaurace;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class OrderList {


    //List of orders for one table
    public void printOrdersForTable(List<Order> list, int tableNum) {
        int cisloPolozky = 1;

        for (Order order : list) {
            if (cisloPolozky == 1 && order.getTableNum() == tableNum) {
                System.out.println("** Objednávky pro stůl č.  " + order.getTableNum() + " **\n****" + "\n");
            }
            if (cisloPolozky == list.size()) {
                System.out.println("*******");
            }
            if (order.getTableNum() == tableNum) {
                String record = cisloPolozky + ". " + order.getOrderedDish().getDishTitle() + " "
                        + order.getNumOfDishes() + "x ("
                        + order.getSumOfDishesPrice()
                        + " Kč):    " + order.getOrderTime() + "-" + order.getFulfilmentTime()
                        + "  " + "číšník č. " + order.getWaiter().getId();
                cisloPolozky++;
                System.out.println(record);
            }
        }
        System.out.println("*******");
    }


    //List of dishes which were ordered today no matter how many times ordered
    public Set<Dish> getListOfTodayOrderedDishes(List<Order> list) {
        System.out.println("TODAY ORDERED DISHES: \n");
        Set<Dish> todaysDishes = new HashSet<>();
        for (Order order : list) {
            if (order.getDate().equals(LocalDate.now())) {
                todaysDishes.add(order.getOrderedDish());
            }
        }
        System.out.println(todaysDishes);
        return todaysDishes;
    }


    //region 5  Count average time of orders for a specific timeframe Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.


    public void averageOrdersTimeInSpecTimeframe(List<Order> list, LocalTime from, LocalTime to) throws OrdersException {

        if (list.size() == 0) {
            throw new OrdersException("Num of orders or sum of duration must not be zero. " +
                    "Add orders to calculate an average!");
        }

        int numOfOrders = 0;
        long sumOfDurationInMin = 0;


        for (Order order : list) {
            Duration duration;

            if (list.size() == 0) {
                System.out.println("Num of orders or sum of duration must not be zero");
            } else if ((order.getOrderTime().equals(from) || order.getOrderTime().isAfter(from))
                    && (order.getFulfilmentTime().equals(to) || order.getFulfilmentTime().isBefore(to))
                    && (order.getFulfilmentTime().isAfter(from))) {
                duration = Duration.between(order.getOrderTime(), order.getFulfilmentTime());
                System.out.println("objednávka id: " + order.getOrderId() + ", čas objednávky: "
                        + order.getOrderTime() + " konec: " + order.getFulfilmentTime());
                sumOfDurationInMin += duration.toMinutes();
                numOfOrders++;
            }
        }
        try {
            int averageTime = (int) sumOfDurationInMin / numOfOrders;

            String result = "Average time of fulfilling orders is " + averageTime + " minutes.";
            System.out.println(result);

        } catch (ArithmeticException e) {
            throw new OrdersException("No orders available in a given timeframe/" +
                    " Dividing by zero is not possible/\n" + e.fillInStackTrace());
        }
    }


    //sum price for all orders of each waiter and number of orders of each waiter  ok
    public void countSumPriceAndNumOfOrders(List<Waiter> waiterList, List<Order> orderList) {
        String waiterName = "";

        for (Waiter waiter : waiterList) {
            BigDecimal sumPrice = BigDecimal.ZERO;
            int numOfOrders = 0;

            for (Order order : orderList) {
                if (order.getWaiter().equals(waiter)) {
                    numOfOrders++;
                    sumPrice = sumPrice.add(order.getOrderedDish().getPrice());
                    waiterName = order.getWaiter().getName();
                }
            }
            System.out.println(waiterName + ": Sum price: " + sumPrice + ", Number of orders: " + numOfOrders);
        }
    }


    public void sortOrdersByWaiter(List<Order> list) {
        Collections.sort(list);
    }

    public void sortOrdersByOrderTime(List<Order> list) {
        list.sort(Comparator.comparing(Order::getOrderTime));
    }

    // make list of finished orders
    public List<Order> finishedOrders(List<Order> list) {
        List<Order> finishedOrders = new ArrayList<>();
        System.out.println("Id of finished orders: ");
        for (Order order : list) {
            if (!order.getFulfilmentTime().equals(order.getOrderTime())) {
                finishedOrders.add(order);
            }
            System.out.print(order.getOrderId() + " ");
        }
        return finishedOrders;
    }

    //count which orders are ongoing
    public int countOngoingOrders(List<Order> list) {
        int numOfOngoingOrders = 0;

        for (Order order : list) {
            if (!order.isFinished()){
                numOfOngoingOrders++;
            }
        }
        return numOfOngoingOrders;
    }

    //region write/read list of orders into file

    public void readOrdersFromFile(String fileName, String delimiter, List<Order> orderList) throws OrdersException {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] items = line.split(delimiter);

                Order order = new Order(Integer.parseInt(items[0]),
                        Integer.parseInt(items[1]),
                        LocalTime.parse(items[2]),
                        new Dish(items[3]),
                        Integer.parseInt(items[4]),
                        new Waiter(items[5]),
                        LocalTime.parse(items[6]),
                        items[7]
                );

                orderList.add(order);
            }

            printBriefOrderList(orderList);

        } catch (FileNotFoundException e) {
            throw new OrdersException("Unable read from file. " + e.getLocalizedMessage());
        } catch (IllegalArgumentException e) {
            throw new OrdersException("Wrong datatype of value" + e.getLocalizedMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new OrdersException("Missing parameter - " + e.getLocalizedMessage());
        }
    }

    public void writeOrdersIntoFile(List<Order> list, String fileName) throws OrdersException {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Order order : list) {
                String delimiter = ";";
                String record = order.getOrderId() + delimiter
                        + order.getTableNum() + delimiter
                        + order.getOrderTime() + delimiter
                        + order.getOrderedDish().getDishTitle() + delimiter
                        + order.getNumOfDishes() + delimiter
                        + order.getWaiter().getName() + delimiter
                        + order.getFulfilmentTime() + delimiter
                        + order.getNote();
                writer.println(record);
            }
        } catch (IOException e) {
            throw new OrdersException(e.getLocalizedMessage());
        }
    }
    //endregion

    public void addOrderToOrderList(Order order, List<Dish> actualMenu
            , List<Order> list) {
        if (actualMenu.contains(order.getOrderedDish())) {
            list.add(order);
        } else {
            System.out.println("Order Id: " + order.getOrderId() +
                    " ( " + order.getOrderedDish().getDishTitle() + ") dish N/A ... Order not accepted.");
        }
    }

    public void printBriefOrderList(List<Order> list) {
        for (Order order : list) {
            System.out.println("Order Id: "
                    + order.getOrderId() + " ( "
                    + " Title: " + order.getOrderedDish().getDishTitle()
                    + " Waiter: " + order.getWaiter()
                    + " Order Time: " + order.getOrderTime() + ")" + " dish available");
        }
    }
}