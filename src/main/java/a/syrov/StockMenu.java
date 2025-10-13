package a.syrov;

import a.syrov.warehouse.dao.StockDAO;
import a.syrov.warehouse.entity.Stock;

import java.util.List;
import java.util.Scanner;

public class StockMenu {
    private Scanner scanner;
    private StockDAO stockDAO;

    public StockMenu(Scanner scanner, StockDAO stockDAO) {
        this.scanner = scanner;
        this.stockDAO = stockDAO;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== Склад ===");
            System.out.println("1. Показать остатки");
            System.out.println("0. Назад");
            System.out.print("Ваш выбор: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showStock();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Неверный ввод!");
            }
        }
    }

    private void showStock() {
        List<Stock> stocks = stockDAO.getAll();
        if (stocks.isEmpty()) {
            System.out.println("Склад пустой!");
        } else {
            System.out.println("=== Остатки на складе ===");
            for (Stock stock : stocks) {
                System.out.println(stock);
            }
        }
    }
}
