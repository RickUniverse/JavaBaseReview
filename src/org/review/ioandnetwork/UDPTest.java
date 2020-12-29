package org.review.ioandnetwork;

import java.io.IOException;
import java.net.*;

/**
 * @author lijichen
 * @date 2020/8/5 - 19:46
 */
public class UDPTest {
    public static void main(String[] args) throws IOException {
        DatagramSocket gramS = new DatagramSocket();//数据报

        byte[] bytes = "是打发斯蒂芬".getBytes();
        DatagramPacket gramP = new DatagramPacket(bytes,0,bytes.length, InetAddress.getByName("127.0.0.1"),9999);

        gramS.send(gramP);

        gramS.close();
    }
}
class Receiver{
    public static void main(String[] args) throws IOException {
        DatagramSocket gramS = new DatagramSocket(9999);

        byte[] bytes = new byte[1024];
        DatagramPacket gramP = new DatagramPacket(bytes,0,bytes.length);

        gramS.receive(gramP);
        System.out.println(new String(gramP.getData(),0,gramP.getLength()));
        gramS.close();
    }
}