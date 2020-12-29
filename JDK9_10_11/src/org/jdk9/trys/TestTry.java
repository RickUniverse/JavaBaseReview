package org.jdk9.trys;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * @author lijichen
 * @date 2020/8/9 - 15:31
 */
public class TestTry {
    public static void main(String[] args) {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        try (inputStreamReader;outputStreamWriter) {//自动关闭
            char[] chars = new char[20];
            int len;
            if ((len = inputStreamReader.read(chars)) != -1){
                System.out.println(new String(chars,0,len));
            }
            outputStreamWriter.write(new String(chars,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
