package org.review.io;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/4 - 17:34
 */
public class BufferedReadTest {
    public static void main(String[] args) {
        BufferedReader bufRead = null;
        BufferedWriter bufWrit = null;
        try {
            bufRead = new BufferedReader(new FileReader(new File("123.txt")));
            bufWrit = new BufferedWriter(new FileWriter(new File("321.txt")));

            //方式一
            /*char[] cha = new char[5];
            int len;
            while (((len = bufRead.read(cha)) != -1)) {
                bufWrit.write(cha,0,len);
            }*/
            //方式二
            String str;
            while (((str = bufRead.readLine()) != null)) {
//                bufWrit.write(str+"\n");
                bufWrit.write(str);
                bufWrit.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufWrit != null) {
                try {
                    bufWrit.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufRead != null) {
                try {
                    bufRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
