package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable{
    private Socket serverSocket;
    //recive information
    private BufferedReader in;
    //send information

    ServerConnection(Socket socket) throws IOException {
        this.serverSocket=socket;
        this.in=new BufferedReader(new InputStreamReader(this.serverSocket.getInputStream()));

    }
    @Override
    public void run() {
        while(true){
            String serverResponse= null;
            try {
                serverResponse = in.readLine();
                System.out.println(serverResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
