package org.review.io;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/4 - 17:54
 */
public class EncryptionTest {
    public static void main(String[] args) {
        //加密
        encryption("123.txt","111.txt");
        //解密
        encryption("111.txt","222.txt");
    }
    static void encryption(String srcPath,String basePath) {
        BufferedInputStream bufInput = null;
        BufferedOutputStream bufOut = null;
        try {
            bufInput = new BufferedInputStream(new FileInputStream(srcPath));
            bufOut = new BufferedOutputStream(new FileOutputStream(basePath));

            byte[] bytes = new byte[1024];
            int len;
            while (((len = bufInput.read(bytes)) != -1)) {
                for (int i = 0; i < len; i++) {
                    bytes[i] = (byte) (bytes[i] ^ 5);
                }
                bufOut.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufOut != null) {
                try {
                    bufOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufInput != null) {
                try {
                    bufInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
