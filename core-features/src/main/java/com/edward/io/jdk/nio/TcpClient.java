package com.edward.io.jdk.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * Created by edwardcheng on 2017/9/3.
 */
public class TcpClient {

    private static final String HOST_NAME = "127.0.0.1";
    private static final int PORT = 9090;

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;

        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(HOST_NAME, PORT));

            if (socketChannel.finishConnect()){
                int i = 0;
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    String info = "I'm " + i++ + "-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();

                    while (buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (socketChannel != null){
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
