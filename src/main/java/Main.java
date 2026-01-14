import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataRetriever dataRetriever = new DataRetriever();
        Dish dish = dataRetriever.findDishById(1);
        System.out.println(dish);

        dish.setIngredients(List.of(new Ingredient(1), new Ingredient(2)));
        Dish newDish = dataRetriever.saveDish(dish);
        System.out.println(newDish);
    }
}
