package org.review.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author lijichen
 * @date 2020/8/4 - 16:08
 */
public class FileWirterTest {
    public static void main(String[] args) {
//        File file = new File("hello.txt");
//        FileWriter writer = new FileWriter(file,true);
//        writer.write("my name is lijichen \n".toCharArray());
//        writer.write("fuck you!!!!");
//        writer.close();
        FileReader reader = null;
        FileWriter writer = null;

        try {
            //复制操作
            File file = new File("hello.txt");
            File file2 = new File("hello2.txt");

            reader = new FileReader(file);
            writer = new FileWriter(file2);

            char[] cha = new char[5];
            int len;
            while ((len = reader.read(cha)) != -1){
                writer.write(cha,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
