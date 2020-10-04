package proyecto.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseUtil {

    private static final String URL = "jdbc:mysql://isil-ms-01.cw6awrgo5p6n.us-east-1.rds.amazonaws.com:3306/isil_db";
    private static final String USER = "isil_user";
    private static final String PASSWORD = "isil2020";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        //valida el registro del Driver
        Class.forName(DRIVER);
        //realizar la conexion
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }

    public static void main(String[] args) {

        try {
            DataBaseUtil.getConnection();
            System.out.println("Connection success!");

        } catch (ClassNotFoundException e) {
            System.err.println("El driver no existe!");
        } catch (SQLException throwables) {
            System.err.println("No se estableció la conexión!");
        }

    }

}
