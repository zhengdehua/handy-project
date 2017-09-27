package com.edward.io.jdk.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Random;

/**
 * Created by edwardcheng on 2017/9/25.
 */
public class AESExample {

    private static final String AES_MODE = "AES/CBC/PKCS5PADDING"; //AES/CBC/NoPadding
    private static final String AES = "AES";
    private static final String ivParameter = "ABCHINA..ANIHCBA";

    public static String encrypt(String src, String key) throws Exception {
        return encrypt(src.getBytes("utf-8"),key);
    }

    public static String encrypt(byte[] src, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(AES_MODE);
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(src);
        return byte2hex(encrypted);
    }

    public static byte[] decrypt(String src, String key) throws Exception {
        return decrypt(src.getBytes("utf-8"), key);
    }

    public static byte[] decrypt(byte[] src, String key) throws Exception {
        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, AES);
        Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes("utf-8"));
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1 =hex2byte(src);
        byte[] original = cipher.doFinal(encrypted1);
        return original;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException();
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static String generateAesKey(int length) { // length可选16，24，32

        String radStr = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int randNum = rand.nextInt(36);
            generateRandStr.append(radStr.substring(randNum, randNum + 1));
        }
        return generateRandStr + "";
    }

    public static void main(String[] args) throws Exception {

        String secretKey = generateAesKey(16);
        System.out.println(String.format("secret key -> %s", secretKey));

        String src = "My password is 123";
        String encryption = encrypt(src, secretKey);
        System.out.println(String.format("encryption -> %s", encryption));

        byte[] decryption = decrypt(encryption, secretKey);
        System.out.println(String.format("decryption -> %s", new String(decryption)));

    }
}
