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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String firstLine = bufferedReader.readLine();
            if (firstLine == null) return;

            if (firstLine.startsWith("GET")) {
                String httpAnswer = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "\r\n" +
                        "<h1>Бар 'У Энтони' работает!</h1>";
                printWriter.println(httpAnswer);
                printWriter.flush();
            }
            //Блок с telnet не смог прикрутить так, чтобы работал одновременно с http в браузере, поэтому telnet закомментирую дальше
           /* printWriter.println("Hello from server! Type stock to see all stock in Database");
            printWriter.flush();

            while (!exit) {
                String request = firstLine;
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
                if (!exit) {
                    request = bufferedReader.readLine();
                    if (request == null) break; // Клиент закрыл соединение
                }

            }*/
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