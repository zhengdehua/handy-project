package com.edward.io.jdk.net.tcp;

import com.edward.io.jdk.concurrent.TaskRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by edwardcheng on 2017/9/10.
 */
public class Client {

    public static void main(String[] args) {

        String host = "localhost";
        int port = 7070;
        int limit = 5000;

        for (int i = 0; i < limit; i++) {
            int finalI = i + 1;
            TaskRunner.add(() -> new Client(finalI, host, port).send(String.format("I am from client [%d]", finalI)));
        }
        TaskRunner.start();
    }

    private int id;
    private String host;
    private int port;

    public Client(int id, String host, int port) {
        this.id = id;
        this.host = host;
        this.port = port;
    }

    public void send(String msg) {

        Socket socket = null;
        OutputStream output = null;
        InputStream input = null;

        try {
            socket = new Socket(host, port);
            output = socket.getOutputStream();
            output.write(Message.serialize(id, msg));
            output.flush();
            input = socket.getInputStream();

            //block until the request data really arrives.
            while (input.available() == 0) {
                Thread.sleep(500);
            }

            byte[] bytes = new byte[input.available()];

            input.read(bytes);
            String response = new String(bytes);
            System.out.println(String.format("client [%d] get response -> %s", id, response));
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
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
