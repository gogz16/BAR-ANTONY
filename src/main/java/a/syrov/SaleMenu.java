package a.syrov;

import java.util.List;
import java.util.Scanner;

public class SaleMenu {
    private final Scanner scanner;
    private final Bar bar;

    public SaleMenu(Scanner scanner, Bar bar) {
        this.scanner = scanner;
        this.bar = bar;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Продажа коктейля ===");
            List<Cocktails> cocktails = bar.getCocktails();

            if (cocktails.isEmpty()) {
                System.out.println("Нет рецептов! Сначала добавьте.");
                return;
            }

            for (int i = 0; i < cocktails.size(); i++) {
                System.out.println((i + 1) + ". " + cocktails.get(i).getName());
            }
            System.out.println("0. Назад");

            System.out.print("Ваш выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) return;
            if (choice < 1 || choice > cocktails.size()) {
                System.out.println("Неверный ввод!");
                continue;
            }

            System.out.print("Сколько штук продать?: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            bar.sellCocktail(cocktails.get(choice - 1).getName(), count);
        }
    }
}
