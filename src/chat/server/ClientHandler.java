package chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

// класс для работы к клиентами
public class ClientHandler {

    private MainServer server;
    private Socket socket = null;
    private DataOutputStream out;
    private DataInputStream in;
    private String nick;
    ArrayList<String> blackList;

    public String getNick() {
        return nick;
    }

    public ClientHandler(MainServer server, Socket socket) {

        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.blackList = new ArrayList<>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // цикл для авторизации
                        while (true) {
                            String str = in.readUTF();
                            // если приходит сообщение начинающееся с /auth значит пользователь хочет авторизоваться
                            if(str.startsWith("/auth")) {
                                String[] tokens = str.split(" ");
                                // запрашиваем ник в БД
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                               // если ответ не равен null отправляем ответ клиенту о том, что авторизация прошла успешно
                                if(newNick != null) {
                                    if(server.isUserSubscribed(newNick)) {
                                        sendMsg("User already logged in");
                                    } else {
                                        sendMsg("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    }
                                } else {
                                    sendMsg("Wrong Login/Password!");
                                }
                            }
                        }
                        // цикл для работы
                        while (true) {
                            String str = in.readUTF();
                            if(str.startsWith("/")) {
                                if(str.equals("/end")) {
                                    out.writeUTF("/serverClosed");
                                    break;
                                }
                                if(str.startsWith("/w ")) {
                                    String[] tokens = str.split(" ", 3);
                                    if(tokens.length==3) {
                                        server.privateMsg(ClientHandler.this, tokens[1], tokens[2]);
                                    }
                                }
                                if(str.startsWith("/blacklist ")) {
                                    String[] tokens = str.split(" ");
                                    blackList.add(tokens[1]);
                                    sendMsg("You've added user " + tokens[1] + " to blacklist");
                                }
                            } else {
                                server.broadCastMsg(ClientHandler.this, str);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        server.unsubscribe(ClientHandler.this);
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkBlackList(String nick) {
        return blackList.contains(nick);
    }

    // метод для оправки сообщения 1 клиенту
    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
