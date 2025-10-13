package a.syrov;

import a.syrov.warehouse.component.ClientHandler;
import a.syrov.warehouse.dao.StockDAO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started");
            while (true) {
                Socket client = serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(client));
                thread.start();
            }
        }
    //    Scanner scanner = new Scanner(System.in);
    //     Bar bar = new Bar();
     //   StockDAO stockDAO = new StockDAO();

//        stockDAO.save(new Stock(1,"Rum",50));  //  Добавление в таблицу, перед удалением комментария DELETE FROM stock из консоли
//        stockDAO.save(new Stock(2,"Vodka",50));
//        stockDAO.save(new Stock(3,"Cola",200));
//        stockDAO.save(new Stock(4,"Mint",30));
//        stockDAO.save(new Stock(5,"Sugar",20));
//        stockDAO.save(new Stock(6,"Juice",40));
//        stockDAO.save(new Stock(7,"Milk",30));

       /* bar.addIngredient("Rum", 50);
        bar.addIngredient("Vodka", 50);
        bar.addIngredient("Cola", 200);
        bar.addIngredient("Mint", 30);
        bar.addIngredient("Sugar", 20);
        bar.addIngredient("Juice", 40);
        bar.addIngredient("Milk", 30);

        */

        /*Map<String, Integer> mojitoRecipe = new HashMap<>();
        mojitoRecipe.put("Rum", 10);
        mojitoRecipe.put("Mint", 5);
        mojitoRecipe.put("Sugar", 3);
        mojitoRecipe.put("Cola", 50);
        bar.addCocktail(new Cocktails("Mojito", mojitoRecipe));

        Map<String, Integer> pinaColadaRecipe = new HashMap<>();
        pinaColadaRecipe.put("Rum", 15);
        pinaColadaRecipe.put("Juice", 20);
        pinaColadaRecipe.put("Milk", 10);
        bar.addCocktail(new Cocktails("Pina Colada", pinaColadaRecipe));

        System.out.println("Добро пожаловать в бар 'У Энтони'!");


        System.out.println("\n=== Доступные рецепты ===");
        bar.listCocktails();


        MainMenu menu = new MainMenu(scanner, bar);
        menu.show(); */




        /*stockDAO.save(new Stock(1,"Rum",50));  //  Добавление в таблицу
        stockDAO.save(new Stock(2,"Vodka",50));
        stockDAO.save(new Stock(3,"Cola",200));
        stockDAO.save(new Stock(4,"Mint",30));
        stockDAO.save(new Stock(5,"Sugar",20));
        stockDAO.save(new Stock(6,"Juice",40));
        stockDAO.save(new Stock(7,"Milk",30));

        System.out.println("=== Остатки на складе ===");
        for (Stock stocks : stockDAO.getAll()) {
            System.out.println(stocks);
        }*/
    }
}
