package a.syrov;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    Datasource datasource = Datasource.INSTANCE;

    public void save(Stock stock) {
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO warehouse.stock " +
                    "(id,ingredient, quantity) " +
                    "VALUES (?,?,?)");
            preparedStatement.setInt(1, stock.getId());
            preparedStatement.setString(2, stock.getIngredient());
            preparedStatement.setInt(3, stock.getQuantity());
            preparedStatement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            datasource.releaseConnection(connection);
        }
    }

    public List<Stock> getAll() {
        List<Stock> stocks = new ArrayList<>();
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM warehouse.stock");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Stock stock = new Stock(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
                stocks.add(stock);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            datasource.releaseConnection(connection);
        }
        return stocks;
    }

    public void updateQuantity(String ingredient, int newQuantity) {
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE warehouse.stock SET quantity = ? WHERE ingredient = ?");
            preparedStatement.setInt(1,newQuantity);
            preparedStatement.setString(2,ingredient);
            preparedStatement.executeUpdate();
            }
        catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            datasource.releaseConnection(connection);
        }
    }

    public Stock findByIngredient(String ingredient) {
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM warehouse.stock WHERE ingredient = ?");
            preparedStatement.setString(1,ingredient);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Stock(resultSet.getInt("id"),
                        resultSet.getString("ingredient"),
                        resultSet.getInt("quantity"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            datasource.releaseConnection(connection);
        }
        return null;
    }
}
