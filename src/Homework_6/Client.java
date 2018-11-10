package Homework_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        final String IP_ADRESS = "localhost";
        final int PORT = 9999;

        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        try {
            socket = new Socket(IP_ADRESS, PORT);
            System.out.println("Connected to server");

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            Socket finalSocket = socket;
            new Thread(() -> {
                try {
                    while (true) {
                        String str = in.readUTF();
                        System.out.println("Server: " + str);
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
                        finalSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {
                Scanner scanner = new Scanner(System.in);
                scanner.useDelimiter("\\n");
                String txtFromKeyb = scanner.nextLine();
                out.writeUTF(txtFromKeyb);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
