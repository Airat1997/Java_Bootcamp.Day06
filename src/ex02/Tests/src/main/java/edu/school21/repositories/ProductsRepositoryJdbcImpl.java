package edu.school21.repositories;

import edu.school21.models.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    DataSource dataSource;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> findAll() {
        String sqlQuery = "SELECT * FROM products;";
        List<Product> allProducts = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                allProducts.add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allProducts;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sqlQuery = "SELECT * FROM products WHERE id = " + id + ";";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                return Optional.of(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void update(Product product) {
        String sqlQuery = "UPDATE products "
                + "SET name = '" + product.getName() + "', "
                + "price = " + product.getPrice()
                + "WHERE id = " + product.getId() + ";";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Product product) {
        String sqlQuery = "INSERT INTO products (id, name, price) VALUES (" +
                product.getId() + ", '" +
                product.getName() + "', " +
                product.getPrice() + ");";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            int resultSet = statement.executeUpdate(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sqlQuery = "DELETE FROM products WHERE id = " + id + ";";
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
