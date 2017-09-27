package com.edward.io.jdk.net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by edwardcheng on 2017/9/12.
 */
public class Client extends Thread{

    private static final int PORT = 8888;

    @Override
    public void run() {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (socket != null) {
            try {
                System.out.print("send -> ");

                String line = reader.readLine();
                while (line == null || line.trim().length() == 0) {
                    line = reader.readLine();
                }

                byte[] _data = line.getBytes();
                InetAddress addr = InetAddress.getByName("localhost");
                DatagramPacket _packet = new DatagramPacket(_data, _data.length, addr, PORT);
                socket.send(_packet);

                byte[] data = new byte[1024 * 5];
                DatagramPacket packet = new DatagramPacket(data, data.length);
                socket.receive(packet);
                String msg = new String(packet.getData(), 0, packet.getLength());
                System.out.println(String.format("server -> %s", msg));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Client().start();
    }

}
