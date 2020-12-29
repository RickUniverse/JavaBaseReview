package org.review.io;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.nio.Buffer;

/**
 * @author lijichen
 * @date 2020/8/4 - 16:38
 */
public class FileInputStearmTest {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
//        copyFile("");
        System.out.println(l - System.currentTimeMillis());
    }
    static void copyFile(String srcPith,String bashPith){
        FileInputStream input = null;
        FileOutputStream out = null;
        try {
            File file = new File(srcPith);
            File file2 = new File(bashPith);

            input = new FileInputStream(file);
            out = new FileOutputStream(file2);

            byte[] buffer = new byte[5];
            int len;
            while (((len = input.read(buffer)) != -1)) {
                out.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (input != null)
                        input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
