package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    //read information
    private BufferedReader in;
    //send information
    private PrintWriter out;
    private ArrayList<ClientHandler>clients;
    private String name;
    private final int number;
    public ClientHandler(Socket clientSocket, ArrayList<ClientHandler>clients,int number) throws IOException {
        this.name="Client"+Integer.toString(number);
        this.number=number;
        this.clientSocket=clientSocket;
        this.in=new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        out=new PrintWriter(new PrintWriter(this.clientSocket.getOutputStream()),true);
        this.clients=clients;
    }


    @Override
    public void run() {
        try{
            serverToAll(name+" has joined the game");
            this.out.println("/all to send messages");
            while(true){


                String request=this.in.readLine();
                if(request.equals("Who is there")){
                    out.println("This is the server speaking");
                }
                else if(request.startsWith("/all")){
                    outToAll(request.substring(4));
                }
                else{
                    out.println("Waiting for players...");

                }
                System.out.println(Server.client_count);
                System.out.println(Server.numberOfPlayers);

            }

        } catch (IOException e) {
            System.err.println("Io error");
            throw new RuntimeException(e);
        }

    }

    private void outToAll(String message) {
        for(ClientHandler client:clients){
            if(client.name.equals(this.name)){
                client.out.println("You: "+message);
            }
            else{
            client.out.println(this.name+": "+message);}
        }
    }
    private void serverToAll(String message){
        for(ClientHandler client:clients){
            client.out.println("Server: "+message);
        }
    }
}
