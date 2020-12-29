package org.review.io;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/4 - 17:10
 */
public class BufferTest {
    public static void main(String[] args) {
        BufferedInputStream bufferInput = null;
        BufferedOutputStream bufferOut = null;
        try {
            File file = new File("1.png");
            File file2 = new File("3.png");

            FileInputStream input = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(file2);

            bufferInput = new BufferedInputStream(input);
            bufferOut = new BufferedOutputStream(out);

            byte[] bytes = new byte[1024];
            int len;
            while (((len = bufferInput.read(bytes)) != -1)) {
                bufferOut.write(bytes,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferInput != null) {
                try {
                    bufferOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferOut != null) {
                try {
                    bufferInput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
