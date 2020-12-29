package org.review.ioandnetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author lijichen
 * @date 2020/8/5 - 16:14
 */
public class RandomAccessFileTest2 {
    public static void main(String[] args) {
        RandomAccessFile raf1 = null;
        try {
            raf1 = new RandomAccessFile("hello.txt","rw");

            raf1.seek(3);//将指针移动到3位置下
            StringBuilder sb = new StringBuilder((int) new File("hello.txt").length());
            byte[] bytes = new byte[20];
            int len;
            while (((len = raf1.read(bytes)) != -1)) {
                sb.append(new String(bytes,0,len));
            }
            raf1.seek(3);//调回指针
            raf1.write("xyz".getBytes());
            raf1.write(sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
