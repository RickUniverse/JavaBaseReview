package org.review.io;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/4 - 18:28
 */
public class InputStreamReaderTest {
    public static void main(String[] args) throws IOException {
        //读入为utf-8
        InputStreamReader inRead = new InputStreamReader(new FileInputStream("123.txt"), "utf-8");
        //写入并，修改为gbk
        OutputStreamWriter gbk = new OutputStreamWriter(new FileOutputStream("gbk.txt"), "gbk");

        char[] cha = new char[5];
        int len;
        while (((len = inRead.read(cha)) != -1)) {
            //System.out.print(new String(cha,0,len));
            gbk.write(cha);
        }

        inRead.close();
        gbk.close();
    }
}
