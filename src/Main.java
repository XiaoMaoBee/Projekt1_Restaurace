import com.engeto.projekt1.restaurace.*;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        OrderList orderListObject = new OrderList();
        DishList dishListObject = new DishList();
        Menu menu = new Menu();
        List<Dish> actualMenu = new ArrayList<>();
        List<Dish> dishList = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        List<Waiter> waiterList = new ArrayList<>();
        List<Order> testList = new ArrayList<>();
        String gap = "\n\n\n";



        //region Přidej do systému dva číšníky a tři stoly s čísly 1, 2 a 15.
        Waiter waiterLenkaR_1 = new Waiter("Lenka Rychlá");
        Waiter waiterJaroslavO_2 = new Waiter("Jaroslav Osmahlý");
        Waiter waiterLeopoldS_3 = new Waiter("Leopold Smažený");

        waiterList.add(waiterJaroslavO_2);
        waiterList.add(waiterLenkaR_1);
        waiterList.add(waiterLeopoldS_3);

        //endregion

        //region Vytvoř alespoň tři jídla
        Dish rizek = new Dish("Kuřecí řízek obalovaný 150g", CategoryDish.LUNCH, BigDecimal.valueOf(120),
                20, "kureci-rizek-01.png");
        Dish hranolky = new Dish("Hranolky 100g", CategoryDish.SIDE_DISH, BigDecimal.valueOf(60),
                20, "hranolky-01.png");
        Dish pstruh = new Dish("Pstruh na víně 180g", CategoryDish.DINNER, BigDecimal.valueOf(240),
                20, "pstruh-na-vine-01.png");
        Dish pizza30 = new Dish("Pizza Formagy 30cm", CategoryDish.LUNCH, BigDecimal.valueOf(170),
                20, "pizza-formagy30-01.png");
        Dish pancakes = new Dish("Lívance 150g", CategoryDish.DESSERT, BigDecimal.valueOf(240),
                20, "livance-01.png");

        dishList.add(rizek);
        dishList.add(hranolky);
        dishList.add(pstruh);
        dishList.add(pizza30);
        dishList.add(pancakes);
        //endregion

        //region První a třetí jídlo zařaď do aktuálního menu, druhé jídlo nikoli.
        // Případné další můžeš zařadit dle potřeby.
        actualMenu.add(rizek);
        actualMenu.add(pstruh);

        //endregion

        //region Vytvoř alespoň tři objednávky pro stůl číslo 15 a jednu pro stůj číslo 2.
        Order order1 = new Order(15, LocalTime.now(), rizek,
                2, waiterLenkaR_1, "nothing");
        Order order2 = new Order(15, LocalTime.of(12, 25), pstruh,
                1, waiterJaroslavO_2, LocalTime.of(13, 0), "nothing");
        order2.setFinished(true);
        Order order3 = new Order(15, LocalTime.of(12, 10), hranolky,
                3, waiterLenkaR_1,  LocalTime.of(13, 10),"extra plate for a child");
        order3.setFinished(true);
        Order order4 = new Order(3, LocalTime.of(12, 10), rizek,
                5, waiterJaroslavO_2, LocalTime.of(13, 0), "nothing");
        order4.setFinished(true);
        Order order5 = new Order(15, LocalTime.of(12, 25), pancakes,
                3, waiterLeopoldS_3, LocalTime.of(12, 45), "nothing");
        order5.setFinished(true);
        Order order6 = new Order(2, LocalTime.of(13, 10), rizek,
                1, waiterJaroslavO_2, "nothing");
        Order order7 = new Order(15, LocalTime.of(13, 20), pizza30,
                3, waiterLeopoldS_3, "nothing");
        Order order8 = new Order(15, LocalTime.of(13, 20), pstruh,
                5, waiterLeopoldS_3, "nothing");
        Order order9 = new Order(3, LocalTime.of(13, 20), pancakes,
                2, waiterLenkaR_1, "nothing");
        Order order10 = new Order(15, LocalTime.of(13, 20), rizek,
                2, waiterLenkaR_1, "nothing");
        Order order11 = new Order(15, LocalTime.of(13, 20), pancakes,
                3, waiterJaroslavO_2, "nothing");
        Order order12 = new Order(3, LocalTime.of(13, 20), rizek,
                1, waiterJaroslavO_2, "nothing");
        Order order13 = new Order(3, LocalTime.of(7, 20), rizek,
                1, waiterJaroslavO_2, LocalTime.now(), "nothing");
        order13.setFinished(true);
        Order order14 = new Order(3, LocalTime.of(7, 20), rizek,
                1, waiterJaroslavO_2, LocalTime.now(), "nothing");
        order14.setFinished(true);
        Order order15 = new Order(3, LocalTime.of(7, 20), rizek,
                1, waiterJaroslavO_2, LocalTime.now(), "nothing");
        order15.setFinished(true);

        //endregion

        //region Vyzkoušej přidat objednávku jídla, které není v menu — aplikace musí ohlásit chybu.

        System.out.println("Aktuální menu: \n" + actualMenu + "\n");
        System.out.println("6. Vyzkoušej přidat objednávku jídla, které není v menu — aplikace musí ohlásit chybu.\n");

        orderListObject.addOrderToOrderList(order1, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order2, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order3, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order4, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order5, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order6, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order7, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order8, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order9, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order10, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order11, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order12, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order13, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order14, actualMenu, orderList);
        orderListObject.addOrderToOrderList(order15, actualMenu, orderList);
        System.out.println(gap);

        System.out.println("VALID ORDERS WITH AVAILABLE DISH: \n");
        orderListObject.printBriefOrderList(orderList);


        //endregion

        System.out.println(gap);
        // Count ongoing orders
        System.out.println("NUMBER OF ONGOING ORDERS: " +
                orderListObject.countOngoingOrders(orderList));

        // List of finished orders
        System.out.println("\nFINISHED ORDERS: ");
        orderListObject.finishedOrders(orderList);
        System.out.println("\n" + gap);

        // Seřaď objednávky podle číšníka, nebo orderTime
        System.out.println("\nSORTED ORDER LIST BY WAITER\n");
        orderListObject.sortOrdersByWaiter(orderList);
        System.out.print("ORDERLIST PRINTED: \n");
        orderListObject.printBriefOrderList(orderList);
        System.out.println(gap);

        System.out.println("SORTED ORDER LIST BY ORDERTIME\n");
        orderListObject.sortOrdersByOrderTime(orderList);
        System.out.print("ORDERLIST PRINTED: \n"); orderListObject.printBriefOrderList(orderList);

        // Check a dish availability
        System.out.println(gap);
        System.out.println("CHECK A DISH AVAILABILITY");
        menu.checkDishAvailability(actualMenu, rizek);
        System.out.println(gap);

        System.out.println("LIST OF ORDERS FOR ONE TABLE: ");
        orderListObject.printOrdersForTable(orderList, 3);
        System.out.println(gap);

        System.out.println("LIST OF TODAY ORDERED DISHES: ");
        System.out.println(orderListObject.getListOfTodayOrderedDishes(orderList));
        orderListObject.getListOfTodayOrderedDishes(orderList);
        System.out.println(gap);

        System.out.println("SUM PRICE AND NUMBER OF ASSIGNED ORDERS FOR EACH WAITER: ");
        orderListObject.countSumPriceAndNumOfOrders(waiterList, orderList);
        System.out.println(gap);


        System.out.println("AVERAGE TIME OF PROCESSING ORDER IN A GIVEN TIMEFRAME: ");
        testList.add(order1);
        testList.add(order2);
        testList.add(order3);
        testList.add(order4);
        testList.add(order5);
        testList.add(order6);
        try {
            orderListObject.averageOrdersTimeInSpecTimeframe(
                    testList, LocalTime.of(6, 0), LocalTime.now());
        } catch (OrdersException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //region READ/WRITE FROM/TO FILES

        //WRITE ORDERS INTO A FILE

        try {
            orderListObject.writeOrdersIntoFile(orderList, "orders.txt");
        } catch (OrdersException e) {
            System.err.println("File not found or broken or N/A " + e.getLocalizedMessage());
        }

        System.out.println(gap);
        //READ ORDERS FROM FILE

        System.out.println("READ ORDERS FROM FILE");
        try {
            orderListObject.readOrdersFromFile("orders.txt", ";", orderList);//
        } catch (OrdersException e) {
            System.err.println(e.getLocalizedMessage());
        }


        //WRITE MENU INTO A FILE
        System.out.println("WRITE MENU INTO FILE");
        try {
            menu.writeMenuInFile(actualMenu, "menu.txt");
        } catch (OrdersException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //READ MENU FROM FILE
        System.out.println("READ MENU FROM FILE");
        try {
            menu.readMenuFromFile("menu.txt", actualMenu, ";");
        } catch (OrdersException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //WRITE DISH LIST INTO A FILE
        try {
            dishListObject.writeDishListInFile(dishList, "dishes.txt");
        } catch (OrdersException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //READ DISH LIST FROM FILE
        System.out.println("READ DISH LIST FROM FILE");
        try {
            dishListObject.readDishListFromFile("dishes.txt", ";", dishList);
        } catch (OrdersException e) {
            System.err.println(e.getLocalizedMessage());
        }

        //endregion
    }
}



