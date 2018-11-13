package chat.server;

import java.sql.*;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    // метод для подключения к БД
    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            // строка подключения
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB25102018.db");
            // установка подключения
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // метод для запроса пользователя из БД, если искомый пользоватль есть
    // то вернется ник если его нету то вернется null
    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname FROM USERS" +
                " WHERE login = '%s' AND password = '%s'", login, pass);
        try {
            ResultSet rs = stmt.executeQuery(sql);

            if(rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // метод для отключения от БД
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
