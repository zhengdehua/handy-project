package com.edward.io.jdk.net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by edwardcheng on 2017/9/12.
 */
public class Server extends Thread{

    private static final int PORT = 8888;

    @Override
    public void run() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (socket != null) {
            try {
                byte[] data = new byte[1024 * 5];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println(String.format("client[%d] -> %s", packet.getPort(), msg));

                if (packet.getLength() > 0) {
                    System.out.print("reply -> ");

                    String line = reader.readLine();
                    while (line == null || line.trim().length() == 0) {
                        line = reader.readLine();
                    }
                    byte[] _data = line.getBytes();
                    DatagramPacket _packet = new DatagramPacket(_data, _data.length, packet.getAddress(), packet.getPort());
                    socket.send(_packet);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }

}
