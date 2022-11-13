package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT=10000;
    private static ArrayList<ClientHandler> clients=new ArrayList<>();
    private static ExecutorService pool= Executors.newFixedThreadPool(4);
    static int client_count=0;
    static int numberOfPlayers=2;
    public static void main(String[] args) throws IOException {
        ServerSocket listener=new ServerSocket(PORT);

        while (true) {


            System.out.println("Waiting for a User to connect");
            Socket client = listener.accept();
            client_count+=1;
            System.out.println("Client number "+client_count+" found");
            ClientHandler clientThread=new ClientHandler(client,clients,client_count);
            clients.add(clientThread);
            pool.execute(clientThread);

        }

    }

}
