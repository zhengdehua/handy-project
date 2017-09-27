package com.edward.io.jdk.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by edwardcheng on 2017/9/3.
 */
public class RandomAccessFileExample {

    public static void main(String[] args) {

        RandomAccessFile accessFile = null;

        try {
            String baseDir = System.getProperty("user.dir") + "/core-features/";
            accessFile = new RandomAccessFile(baseDir + "src/main/resources/test.txt", "rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int bytesRead = fileChannel.read(buffer);

            while (bytesRead != -1) {
                buffer.flip();
                while (buffer.hasRemaining()){
                    System.out.print((char)buffer.get());;
                }
                buffer.compact();
                bytesRead = fileChannel.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (accessFile != null){
                try {
                    accessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
