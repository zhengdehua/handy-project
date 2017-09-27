package com.edward.io.jdk.net.tcp;

import java.io.*;

/**
 * Created by edwardcheng on 2017/9/10.
 */
public class Message implements Serializable {

    private int id;
    private String body;

    public static Message antiSerialize(byte[] bytes) {
        ObjectInputStream ois = null;
        Message msg = null;

        try {
            ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
            msg = (Message)ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return msg;
    }

    public static byte[] serialize(int id, String body) {
        Message msg = new Message();
        msg.setId(id);
        msg.setBody(body);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return bos.toByteArray();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
