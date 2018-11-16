package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Vector;

public class MainServer {

    Vector<ClientHandler> clients;

    public MainServer() throws SQLException {

        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();

            server = new ServerSocket(8189);
            System.out.println("Сервер запущен!");

            while (true) {
                socket = server.accept();
                System.out.println("Клиент подключился!");
                // создаем нового клиента
               new ClientHandler(this, socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }

    }
    // метод для рассылки сообщения всем клиентам
    public void broadCastMsg(ClientHandler from, String msg) {
        for (ClientHandler o: clients) {
            if(!o.checkBlackList(from.getNick())) {
                o.sendMsg(from.getNick() + ": " + msg);
            }
        }
    }

    // method to send private message
    public void privateMsg(ClientHandler from, String nick, String msg) {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nick)) {
                o.sendMsg("[private] " + from.getNick() + ": " + msg);
                from.sendMsg("[private to " + nick + "] " + from.getNick() + ": " + msg);
            }
        }
        from.sendMsg("User with nick " + nick + " not found!");
    }

    // подписываем клиента и добавляем его в список клиентов
    public void subscribe(ClientHandler client) {
        clients.add(client);
    }

    // отписываем клиента и удаляем его из списка клиентов
    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
    }

    // check if user already subscribed
    public boolean isUserSubscribed(String nick) {
        for (ClientHandler o: clients) {
            if(o.getNick().equals(nick)) return true;
        }
        return false;
    }
}
