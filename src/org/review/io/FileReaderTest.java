package org.review.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author lijichen
 * @date 2020/8/4 - 15:24
 */
public class FileReaderTest {
    public static void main(String[] args) {
        File file = new File("123.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileInputStream input = null;
        FileReader reader = null;
        try {
            //字节输入流
//            input = new FileInputStream(file);
//            int read;
//            while ((read = input.read()) != -1) {
//                System.out.println((char)read);
//                System.out.println(read);
//            }
            //节点输入
            reader = new FileReader(file);
            char[] cha = new char[5];
            int len;
            while ((len = reader.read(cha)) != -1){
                //方式一
                /*for (int i = 0; i < len; i++) {
                    System.out.print((char)cha[i]);
                }*/
                //方式二
                String str = new String(cha,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
