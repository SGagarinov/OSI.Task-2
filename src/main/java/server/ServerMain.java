package server;

//Задача "Клиент-сервер с рюшечками"

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        int port = 8085;

        ServerSocket serverSocket = new ServerSocket(port); // порт можете выбрать любой в доступном диапазоне 0-65536. Но чтобы не нарваться на уже занятый - рекомендуем использовать около 8080
        Socket clientSocket = serverSocket.accept(); // ждем подключения
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        System.out.println("New connection accepted");
        String message = in.readLine();
        out.println(String.format("Client message: %s. Client port is %d. Write your name!", message, clientSocket.getPort()));

        String username = in.readLine();
        out.println("Are you child? (yes/no)");

        if (in.readLine().equals("yes")) {
            out.println("Welcome to the kids area, " + username + ". Let's play!");
        } else {
            out.println("Welcome to the adult zone, " + username + ". Have a good rest, or a good working day!");
        }
    }
}
