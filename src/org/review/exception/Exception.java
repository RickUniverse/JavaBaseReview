package org.review.exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author lijichen
 * @date 2020/7/27 - 12:37
 */
public class Exception {
    public static void main(String[] args) {
        method1();
    }
    public static void method1() {

        try {
            method2();
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("一定执行");
        }
    }
    public static void method2() throws IOException, FileNotFoundException{
        method3();
    }
    public static void method3() throws FileNotFoundException,IOException {
        File file = new File("helloworld.txt");
        FileInputStream fis = new FileInputStream(file);

        int data = fis.read();
        while (data != -1){
            System.out.println((char)data);
            data = fis.read();
        }
        fis.close();
    }

}
