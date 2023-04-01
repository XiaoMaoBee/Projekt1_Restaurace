package com.engeto.projekt1.restaurace;

import java.io.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class OrderList {


    //region  7  List of orders for one table in format see zadání       OK

    public String printOrdersForTable(List<Order> list, int tableNum) {
        int cisloPolozky = 1;
        String record = "";
        for (Order order : list) {
            if (cisloPolozky == 1 && order.getTableNum() == tableNum) {
                System.out.println("** Objednávky pro stůl č.  " + order.getTableNum() + " **\n****" + "\n");
            }
            if (cisloPolozky == list.size()) {
                System.out.println("*******");
            }
            if (order.getTableNum() == tableNum) {
                record = cisloPolozky + ". " + order.getOrderedDish().getDishTitle() + " "
                        + order.getNumOfDishes() + "x ("
                        + order.getSumOfDishesPrice()
                        + " Kč):    " + order.getOrderTime() + "-" + order.getFulfilmentTime()
                        + "  " + "číšník č. " + order.getWaiter().getId();
                cisloPolozky++;
                System.out.println(record);
            }
        }
        System.out.println("*******");
        return record;
    }

    //endregion


    //region  6  List of dishes which were ordered today            OK
    // , no matter how many times ordered


    public Set<Dish> getListOfTodayOrderedDishes(List<Order> list) {
        System.out.println("TODAY ORDERED DISHES: \n");
        Set<Dish> todaysDishes = new HashSet<>();
        for (Order order : list) {
            if (order.getDate().equals(LocalDate.now())) {
                todaysDishes.add(order.getOrderedDish());
                // System.out.println(order);
            }
        }
        System.out.println(todaysDishes);
        return todaysDishes;
    }
    //endregion


    //region 5  Count average time of orders for a specific timeframe Průměrnou dobu zpracování objednávek, které byly zadány v určitém časovém období.
    public String AverageOrdersTimeInSpecTimeframe(List<Order> list, LocalTime from, LocalTime to) throws OrdersException {
        int numOfOrders = 0;
        long sumOfTimeOfOrder = 0;
        long sumOfDurationInMin = 0;

        for (Order order : list) {
            Duration duration;

            if ((order.getOrderTime().equals(from) || order.getOrderTime().isAfter(from))
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
            return result;

        } catch (ArithmeticException e) {
            throw new OrdersException("No orders available in a given timeframe/" +
                    " Dividing by zero is not possible/\n" + e.fillInStackTrace());
        }
    }

    //endregion


    //region 4  sum price for all orders of each waiter and number of orders of each waiter  ok
    public String countSumPriceAndNumOfOrders(List<Waiter> waiterList, List<Order> orderList) {
        String waiterName = "";
        String result = "";

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
            result = waiterName + ": Sum price: " + sumPrice + ", Number of orders: " + numOfOrders;
            System.out.println(result);
        }
        return result;
    }


    //endregion


    //region3  sort orders according to waiter or orderTime           OK
    public void sortOrdersByWaiter(List<Order> list) {
        Collections.sort(list);
    }

    public void sortOrdersByOrderTime(List<Order> list) {
        Collections.sort(list, (o1, o2) -> o1.getOrderTime().compareTo(o2.getOrderTime()));
    }
    //endregion


    //region  2  make list of finished orders                                   OK
    public List<Order> FinishedOrders(List<Order> list) {
        List<Order> finishedOrders = new ArrayList<>();
        List<Integer> finishedOrdersId = new ArrayList<>();
        System.out.println("Id of finished orders: ");
        for (Order order : list) {
            if (!order.getFulfilmentTime().equals(order.getOrderTime())) {
                finishedOrders.add(order);
                finishedOrdersId.add(order.getOrderId());
            }
            System.out.print(order.getOrderId() + " ");

        }
        return finishedOrders;
    }
    //endregion


    //region 1  count which orders are ongoing                    OK
    public int countOngoingOrders(List<Order> list) {
        int numOfOngoingOrders = 0;

        for (Order order : list) {
            if (order.getFulfilmentTime() == (LocalTime.of(0, 0))) {
                numOfOngoingOrders++;
            }
        }
        return numOfOngoingOrders;
    }
    //endregion


    //region write/read list of orders into file            OK

    public void readOrdersFromFile(String fileName, String delimiter) throws OrdersException {
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
                System.out.println(order);
            }
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


//    public void writeOrdersIntoFile(List<Order> list, String fileName) throws OrdersException {
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
//            for (Order order : list) {
//                String delimiter = ";";
//                String record = "Order id: " + order.getOrderId() + delimiter
//                        + " Table num.: " + order.getTableNum() + delimiter
//                        + " Time of order: " + order.getOrderTime() + delimiter
//                        + " Ordered dish: " + order.getOrderedDish().getDishTitle() + delimiter
//                        + " Num of dishes: " + order.getNumOfDishes() + delimiter
//                        + " Waiter name: " + order.getWaiter().getName() + delimiter
//                        + " Fulfillment time: " + order.getFulfilmentTime() + delimiter
//                        + " Note: " + order.getNote();
//                System.out.println(record);
//                writer.println(record);
//            }
//        } catch (IOException e) {
//            throw new OrdersException(e.getLocalizedMessage());
//        }
//    }


//    //region  7  List of orders for one table in format see zadání
//    public void printOrdersForTable(int tableNum) {
//        for (Order order : orderList) {
//            if (order.getTableNum() == tableNum) {
//                System.out.println(
//                        "** Objednávky pro stůl č.  " + tableNum + " " + "**\n****\n\n" +
//                                "");
//            }
//        }
//    }
//
//
//
//    public void readTableOrdersFromFile(String fileName) throws OrdersException {
//        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                String record = line;
//                String[] items = line.split(" ");
//
//                Order orderTable = new Order(Integer.parseInt(items[0]),
//                        LocalTime.parse(items[3]),
//                        new Dish(items[0]),
//                        Integer.parseInt(items[5]),
//                        new Waiter(items[2]),
//                        LocalTime.parse(items[6]),
//                        items[7]
//                );
//            }
//        } catch (FileNotFoundException e) {
//            throw new OrdersException(e.getLocalizedMessage());
//        }
//    }
//
//    public void writeTableOrdersIntoFile(List<Order> list,String filename, int tableNum) {
//        int cisloPolozky = 1;
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)))) {
//            for (Order order : list) {
//                if (order.getTableNum() == tableNum) {
//                    // System.out.println("** Objednávky pro stůl č.  " + order.getTableNum() + " **\n****" + "\n\n");
//                    String header = "** Objednávky pro stůl č.  " + order.getTableNum() + " **\n****" + "\n\n";
//                    String record = header + cisloPolozky + ". " + order.getOrderedDish().getDishTitle() + " "
//                            + order.getNumOfDishes() + "x ("
//                            + order.getSumOfDishesPrice()
//                            + " Kč):    " + order.getOrderTime() + "-" + order.getFulfilmentTime()
//                            + "  " + "číšník č. " + order.getWaiter().getId();
////                    System.out.println(record);
//                    writer.println(record);
//                }
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//
//    }
////endregion

