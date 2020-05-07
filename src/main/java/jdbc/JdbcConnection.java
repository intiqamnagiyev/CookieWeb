package jdbc;

import java.sql.*;

public class JdbcConnection {
    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String NAME = "postgres";
    private final static String PWD = "test123456";

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, NAME, PWD);

        conn.setAutoCommit(false);
        try {

            //
            // update x set salary = 120 where id = 1
            // update x set salary = 80 where id = 2
            //
            //
            conn.commit();
        } catch (RuntimeException x) {
            //
            //
            conn.rollback();
        }

    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, NAME, PWD);
    }
}
