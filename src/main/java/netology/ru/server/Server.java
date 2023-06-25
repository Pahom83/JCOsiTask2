package netology.ru.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    final static int port = 8080;
    private static String name;
    private static int age;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) { //Создаем серверный сокет
            Socket clientSocket = serverSocket.accept(); // ждем подключения
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            int messageCount = 0;
            while (true) {
                if (messageCount == 0) {
                    output.println("Connect is successful. Input your name");
                    messageCount++;
                } else {
                    String s = input.readLine();
                    if (messageCount == 1) {
                        name = s;
                        output.println(message(messageCount));
                        messageCount++;
                    } else if (messageCount == 2) {
                        age = Integer.parseInt(s);
                        output.println(message(messageCount));
                        messageCount++;
                    } else if (s.equals("exit")) {
                        output.println("Server is closed connection");
                        serverSocket.close();
                        clientSocket.close();
                        break;
                    } else {
                        output.println(message(messageCount));
                        messageCount++;
                    }
                }
            }
            output.println("End");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String message(int messageCount) {
        if (messageCount == 1) {
            return "Welcome, " + name + ". Input your age";
        } else if (messageCount == 2) {
            if (age >= 18) {
                return "Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!";
            } else {
                return "Welcome to the kids area, " + name + "! Let's play!";
            }
        }
        return "Input \"exit\" for close connection";
    }
}