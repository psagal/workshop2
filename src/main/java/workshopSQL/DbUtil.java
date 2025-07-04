package pl.coderslab.mysql.workshop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/workshop2";
    private static final String USER = "root";
    private static final String PASSWORD = "coderslab";

    public static Connection connect() throws SQLException {
            return DriverManager.getConnection(URL,USER,PASSWORD);
    }
}
