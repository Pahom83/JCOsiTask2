package netology.ru.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    final static int port = 8080;
    final static String host = "netology.homework";
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket(host, port);
             PrintWriter output = new
                     PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new
                     InputStreamReader(clientSocket.getInputStream()))) {
            Scanner in = new Scanner(System.in);
                while (true){
                    String s = input.readLine();
                    if (s.equals("Server is closed connection")){
                        clientSocket.close();
                        break;
                    }
                    System.out.println(s);
                    output.println(in.nextLine());
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connect closed.");
    }
}