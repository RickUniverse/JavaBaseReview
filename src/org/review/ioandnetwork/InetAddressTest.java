package org.review.ioandnetwork;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lijichen
 * @date 2020/8/5 - 17:31
 */
public class InetAddressTest {
    public static void main(String[] args) {
        try {
            InetAddress inet1 = InetAddress.getByName("www.baidu.com");
            System.out.println(inet1);

            InetAddress inet2 = InetAddress.getByName("192.168.0.107");
            System.out.println(inet2);

            InetAddress inetLocal = InetAddress.getLocalHost();
            System.out.println(inetLocal);
            System.out.println(inetLocal.getHostName());
            System.out.println(inetLocal.getHostAddress());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } finally {
        }

    }
}
