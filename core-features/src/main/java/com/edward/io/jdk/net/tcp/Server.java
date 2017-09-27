package com.edward.io.jdk.net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by edwardcheng on 2017/9/10.
 */
public class Server {

    public static void main(String[] args) {

        int port = 7070;
        new Server(port).listen();
    }

    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void listen() {

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (serverSocket != null) {
            try {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> receive(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void receive(Socket socket) {

        PrintWriter writer = null;
        InputStream input = null;

        try {
            writer = new PrintWriter(socket.getOutputStream());
            input = socket.getInputStream();

            //block until the request data really arrives.
            while (input.available() == 0) {
                Thread.sleep(500);
            }

            byte[] bytes = new byte[input.available()];

            input.read(bytes);
            Message request = Message.antiSerialize(bytes);
            System.out.println(String.format("server get request -> %s", request.getBody()));
            writer.println(String.format("welcome client [%d]", request.getId()));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (writer != null) {
                writer.close();
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
