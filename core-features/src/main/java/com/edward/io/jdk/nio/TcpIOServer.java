package com.edward.io.jdk.nio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by edwardcheng on 2017/9/3.
 */
public class TcpIOServer {

    private static final int PORT = 9090;

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        InputStream in = null;

        try {
            serverSocket = new ServerSocket(PORT);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];

            while (true) {
                Socket clientSocket = serverSocket.accept();
                SocketAddress clientAddress = clientSocket.getRemoteSocketAddress();
                System.out.println("Handling client at " + clientAddress);
                in = clientSocket.getInputStream();

                while ((recvMsgSize = in.read(recvBuf)) != -1) {
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
