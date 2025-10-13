package a.syrov.warehouse.component;

import a.syrov.warehouse.dao.StockDAO;
import a.syrov.warehouse.entity.Stock;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket client) {
        socket = client;
    }

    @Override
    public void run() {
        boolean exit = false;
        try (InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream()) {
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            printWriter.println("Hello from server! Type stock to see all stock in Database");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (!exit) {
                String request = bufferedReader.readLine();
                switch (request) {
                    case "stock":
                        StockDAO stockDAO = new StockDAO();
                        List<Stock> all = stockDAO.getAll();
                        printWriter.println(all);
                        break;
                    case "exit":
                        printWriter.println("Bye");
                        exit = true;
                        break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
