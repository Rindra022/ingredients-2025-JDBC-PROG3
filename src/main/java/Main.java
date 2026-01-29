import java.time.Instant;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever dr = new DataRetriever();

    /* ===============================
       1️⃣ TEST : table non fournie
       =============================== */
        try {
            Order order = new Order();
            order.setDishOrderList(List.of()); // même vide

            dr.saveOrder(order);
        } catch (Exception e) {
            System.out.println("TEST 1 OK ➜ " + e.getMessage());
        }

    /* ===============================
       2️⃣ TEST : table libre + stock OK
       =============================== */
        try {
            Table table1 = new Table();
            table1.setId(1);
            table1.setNumber(1);

            TableOrder tableOrder = new TableOrder();
            tableOrder.setTable(table1);
            tableOrder.setArrivalDatetime(
                    Instant.parse("2026-01-29T12:00:00Z"));
            tableOrder.setDepartureDatetime(
                    Instant.parse("2026-01-29T13:00:00Z"));

            Dish dish = dr.findDishById(1);

            DishOrder dishOrder = new DishOrder();
            dishOrder.setDish(dish);
            dishOrder.setQuantity(2);

            Order order = new Order();
            order.setDishOrderList(List.of(dishOrder));
            order.setTableOrder(tableOrder);

            Order saved = dr.saveOrder(order);
            System.out.println("TEST 2 OK ➜ Commande créée : " + saved.getReference());

        } catch (Exception e) {
            System.out.println("TEST 2 ERREUR ➜ " + e.getMessage());
        }

    /* ===============================
       3️⃣ TEST : table déjà occupée
       =============================== */
        try {
            Table table1 = new Table();
            table1.setId(1);
            table1.setNumber(1);

            TableOrder tableOrder = new TableOrder();
            tableOrder.setTable(table1);
            tableOrder.setArrivalDatetime(
                    Instant.parse("2026-01-29T12:30:00Z"));
            tableOrder.setDepartureDatetime(
                    Instant.parse("2026-01-29T13:30:00Z"));

            Dish dish = dr.findDishById(1);

            DishOrder dishOrder = new DishOrder();
            dishOrder.setDish(dish);
            dishOrder.setQuantity(1);

            Order order = new Order();
            order.setDishOrderList(List.of(dishOrder));
            order.setTableOrder(tableOrder);

            dr.saveOrder(order);

        } catch (Exception e) {
            System.out.println("TEST 3 OK ➜ " + e.getMessage());
        }

    /* ===============================
       4️⃣ TEST : stock insuffisant
       =============================== */
        try {
            Table table2 = new Table();
            table2.setId(2);
            table2.setNumber(2);

            TableOrder tableOrder = new TableOrder();
            tableOrder.setTable(table2);
            tableOrder.setArrivalDatetime(
                    Instant.parse("2026-01-29T18:00:00Z"));
            tableOrder.setDepartureDatetime(
                    Instant.parse("2026-01-29T19:00:00Z"));

            Dish dish = dr.findDishById(1);

            DishOrder dishOrder = new DishOrder();
            dishOrder.setDish(dish);
            dishOrder.setQuantity(999); // volontairement trop

            Order order = new Order();
            order.setDishOrderList(List.of(dishOrder));
            order.setTableOrder(tableOrder);

            dr.saveOrder(order);

        } catch (Exception e) {
            System.out.println("TEST 4 OK ➜ " + e.getMessage());
        }

    /* ===============================
       5️⃣ TEST : récupération commande
       =============================== */
        try {
            Order order = dr.findOrderByReference("ORD00001");
            System.out.println("TEST 5 OK ➜ Commande trouvée : " + order.getReference());
        } catch (Exception e) {
            System.out.println("TEST 5 ERREUR ➜ " + e.getMessage());
        }
    }

}
