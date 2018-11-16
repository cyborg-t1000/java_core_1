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
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            // установка подключения
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // метод для запроса пользователя из БД, если искомый пользоватль есть
    // то вернется ник если его нету то вернется null
    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname, password FROM main\n" +
                "WHERE login = '%s'", login);

        int myhash = pass.hashCode();

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);

                if (myhash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(String login, String pass, String nick) {
        String sql = String.format("INSERT INTO main (login, password, nickname)" +
                "VALUES ('%s', '%s', '%s')", login, pass.hashCode(), nick);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
