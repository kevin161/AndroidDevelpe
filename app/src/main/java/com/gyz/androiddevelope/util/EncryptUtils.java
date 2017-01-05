package com.gyz.androiddevelope.util;

import android.util.Base64;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @version V1.0
 * @FileName: com.gyz.androiddevelope.util.EncryptUtils.java
 * @author: ZhaoHao
 * @date: 2016-12-22 11:14
 */
public class EncryptUtils {
    private static final String TAG = "EncryptUtils";
    private final static String secretKey = "www.99fund.com/mobileec/";
    private final static String iv = "htf-fund";
    private final static String encoding = "utf-8";

    /**
     * 3DES加密
     *
     * @param text
     * @return
     */
    public static String encode(String text) {
        Key deskey = null;
        String encodeStr = null;
        try {
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            deskey = keyFactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(text.getBytes(encoding));
            encodeStr = Base64.encodeToString(encryptData, Base64.NO_WRAP).trim();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static String decode(String text) {
        String decodeStr = null;
        Key deskey = null;

        try {

            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("desede");
            deskey = keyFactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
            byte[] decryptData = cipher.doFinal(Base64.decode(text, Base64.NO_WRAP));
            decodeStr = new String(decryptData, encoding);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decodeStr;
    }
}
