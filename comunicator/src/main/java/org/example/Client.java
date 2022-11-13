package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

public class Client {
    private static final int SERVER_PORT = 10000;
    private static final String SERVER_IP = "localhost";
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket(SERVER_IP,SERVER_PORT);
        ServerConnection server=new ServerConnection(socket);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
        new Thread(server).start();
        while(true) {
            //Read input from keyboard
            String keyboardInput = keyboardReader.readLine();
            //send data to server
            out.println(keyboardInput);

            if(keyboardInput.equals("exit"))break;
        }
        socket.close();


    }
}
