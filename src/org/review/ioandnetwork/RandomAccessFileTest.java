package org.review.ioandnetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author lijichen
 * @date 2020/8/5 - 15:48
 */
public class RandomAccessFileTest {
    public static void main(String[] args) {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("222.txt"),"r");
            raf2 = new RandomAccessFile(new File("hello13.txt"),"rw");

            byte[] bytes = new byte[1024];
            int len;
            while (((len = raf1.read(bytes)) != -1)) {
                raf2.write(bytes,0,len);
            }
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
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
