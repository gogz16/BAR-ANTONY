package a.syrov;

import java.util.*;

public class RecipesMenu {
    private final Scanner scanner;
    private final Bar bar;

    public RecipesMenu(Scanner scanner, Bar bar) {
        this.scanner = scanner;
        this.bar = bar;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Работа с коктейлями ===");
            System.out.println("1. Добавить рецепт");
            System.out.println("2. Показать все рецепты");
            System.out.println("3. Найти рецепт по названию");
            System.out.println("0. Назад");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addRecipe();
                    break;
                case 2:
                    bar.listCocktails();
                    break;
                case 3:
                    searchRecipe();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный пункт!");
            }
        }
    }

    private void addRecipe() {
        System.out.print("Название коктейля: ");
        String name = scanner.nextLine();

        Map<String, Integer> ingredients = new HashMap<>();
        while (true) {
            System.out.print("Ингредиент (или 'stop' для завершения): ");
            String ing = scanner.nextLine();
            if (ing.equalsIgnoreCase("stop")) break;
            System.out.print("Количество: ");
            int qty = scanner.nextInt();
            scanner.nextLine();
            ingredients.put(ing, qty);
        }

        bar.addCocktail(new Cocktails(name, ingredients));
        System.out.println("Рецепт добавлен!");
    }

    private void searchRecipe() {
        System.out.print("Введите название: ");
        String name = scanner.nextLine();
        Cocktails c = bar.findCocktail(name);
        if (c != null) {
            System.out.println(c);
        } else {
            System.out.println("Рецепт не найден");
        }
    }
}
