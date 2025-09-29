package a.syrov;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner;
    private final Bar bar; //список с рецептами сразу

    public MainMenu(Scanner scanner, Bar bar) {
        this.scanner = scanner;
        this.bar = bar;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Главное меню ===");
            System.out.println("1. Работа с коктейлями");
            System.out.println("2. Управление запасами");
            System.out.println("3. Продажа коктейля");
            System.out.println("0. Выход");
            System.out.print("Выберите пункт: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    RecipesMenu recipesMenu = new RecipesMenu(scanner, bar);
                    recipesMenu.show();
                    break;
                case 2:
                    StockMenu stockMenu = new StockMenu(scanner, new StockDAO());
                    stockMenu.show();
                    break;
                case 3:
                    SaleMenu saleMenu = new SaleMenu(scanner, bar);
                    saleMenu.show();
                    break;
                case 0:
                    System.out.println("Выход из программы...");
                    return;
                default:
                    System.out.println("Неверный пункт!");
            }
        }
    }
}
