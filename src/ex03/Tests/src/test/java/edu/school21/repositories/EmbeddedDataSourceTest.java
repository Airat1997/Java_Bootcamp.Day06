package edu.school21.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

public class EmbeddedDataSourceTest {

    private DataSource dataSource;

    @BeforeEach
    public void init() {
       /* dataSource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
                .addScript("classpath:schema.sql").addScript("classpath:data.sql").build();*/
    }

    @Test
    public void testConnection() throws SQLException {
        /*Connection connection = dataSource.getConnection();
        assertNotNull(connection);*/
    }

    @AfterEach
    public void tearDown() {

    }
}
