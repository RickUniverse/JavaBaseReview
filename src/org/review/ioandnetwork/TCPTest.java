package org.review.ioandnetwork;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lijichen
 * @date 2020/8/5 - 18:29
 */
class mainTest{
    public static void main(String[] args) {
        server();
    }
    static void server() {
        ServerSocket serSo = null;
        Socket socket = null;//获取socket
        InputStream input = null;
        ByteArrayOutputStream baop = null;
        try {
            serSo = new ServerSocket(9999);
            socket = serSo.accept();
            input = socket.getInputStream();
            //读取输入流中的数据，保存到内置的数组中，然后一次性输出.toString（）
            baop = new ByteArrayOutputStream();

            byte[] bytes = new byte[5];
            int len;
            while (((len = input.read(bytes)) != -1)) {
                baop.write(bytes,0,len);
            }

            String s = baop.toString();
            String hostAddress = socket.getInetAddress().getHostAddress();//获取主机地址
            System.out.println(hostAddress+"："+s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serSo != null) {
                try {
                    serSo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baop != null) {
                try {
                    baop.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
public class TCPTest {
    public static void main(String[] args) {
        client();
    }
    static void client() {
        Socket socket = null;
        OutputStream outPut = null;
        try {
            socket = new Socket("127.0.0.1", 9999);
            outPut = socket.getOutputStream();
            outPut.write("你好，三个发给我啊是的噶尔刚问过".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outPut != null) {
                try {
                    outPut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
