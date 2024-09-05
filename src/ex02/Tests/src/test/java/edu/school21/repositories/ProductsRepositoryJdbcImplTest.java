package edu.school21.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import edu.school21.models.Product;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class ProductsRepositoryJdbcImplTest {

    private static DataSource dataSource;
    private static ProductsRepositoryJdbcImpl productsRepository;

/*
            (1, 'Product 1', 99),
            (2, 'Product 2', 49),
            (3, 'Product 3', 19), -DB
            (4, 'Product 4', 79),
            (5, 'Product 5', 29);
*/

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(1L, "Product 1", 99),
            new Product(2L, "Product 2", 49),
            new Product(3L, "Product 3", 19),
            new Product(4L, "Product 4", 79),
            new Product(5L, "Product 5", 29));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3, "Product 3", 19);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3, "Product 222", 19);
    final Product EXPECTED_SAVED_PRODUCT = new Product(6, "Product 6", 666);

    @BeforeAll
    public static void setUp() {
        dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }
//    @AfterEach
//    public void tearDown() {
//        String sqlQuery = "DROP TABLE PRODUCTS";
//        try {
//            Connection connection = dataSource.getConnection();
//            Statement statement = connection.createStatement();
//            int resultSet = statement.executeUpdate(sqlQuery);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        assertNotNull(connection);
    }

    @Test
    void testFindAll() {
        List<Product> products = productsRepository.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, products);
    }

    @Test
    void testFindById() {
        Product product = productsRepository.findById(3L).get();
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, product);
    }

    @Test
    void testUpdate() {
        Product product = new Product(3, "Product 222", 19);
        productsRepository.update(product);
        assertEquals(EXPECTED_UPDATED_PRODUCT, productsRepository.findById(3L).get());
    }

    @Test
    void testSave(){
        Product product = new Product(6, "Product 6", 666);
        productsRepository.save(product);
        assertEquals(EXPECTED_SAVED_PRODUCT, productsRepository.findById(6L).get());
    }


}
