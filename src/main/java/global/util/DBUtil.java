package global.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    // MySQL JDBC 드라이버 클래스 이름
    private static final String driver = "com.mysql.cj.jdbc.Driver";

    // 데이터베이스 접속 정보
    private static final String url = "jdbc:mysql://localhost:3306/banking";
    private static final String username = "bank";
    private static final String password = "bank1234";

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}

