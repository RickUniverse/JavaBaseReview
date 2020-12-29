package org.review.ioandnetwork;

import org.review.synchronizeds.A;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author lijichen
 * @date 2020/8/5 - 18:55
 */
public class TCP2Test {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost().getHostName(), 9999);
        OutputStream outPut = socket.getOutputStream();
        FileInputStream input = new FileInputStream("1.png");

        byte[] bytes = new byte[1024];
        int len;
        while (((len = input.read(bytes)) != -1)) {
            outPut.write(bytes,0,len);
        }

        socket.shutdownOutput();//说明传输完毕
        //接收服务器端发来的信息
        InputStream serverInput = socket.getInputStream();
        ByteArrayOutputStream baop = new ByteArrayOutputStream();

        int len2;
        while (((len2 = serverInput.read(bytes)) != -1)) {
            baop.write(bytes,0,len2);
        }
        //
        System.out.println(baop.toString());//转换为字符串
        baop.close();
        serverInput.close();
        input.close();
        outPut.close();
        socket.close();
    }
}
class Server{
    public static void main(String[] args) throws IOException {
        ServerSocket ser = new ServerSocket(9999);
        Socket socket = ser.accept();
        InputStream input = socket.getInputStream();
        FileOutputStream outPut = new FileOutputStream("clien.png");

        byte[] bytes = new byte[1024];
        int len;
        while (((len = input.read(bytes)) != -1)) {
            outPut.write(bytes,0,len);
        }

        System.out.println("图片传输完成！");
        //收到返回信息
        OutputStream outPutToClien = socket.getOutputStream();
        outPutToClien.write("图片在服务器端已收到！".getBytes());

        outPutToClien.close();
        outPut.close();
        input.close();
        socket.close();
        ser.close();
    }
}
