package org.jdk9.inputstream;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/9 - 16:16
 */
public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        ClassLoader classLoader = new Test().getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("hello.tst");
        FileOutputStream fileOutputStream = new FileOutputStream("text.txt");
        try (resourceAsStream;fileOutputStream){
            resourceAsStream.transferTo(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
