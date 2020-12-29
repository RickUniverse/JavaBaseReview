package org.nio.nonblockingio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author lijichen
 * @date 2020/10/3 - 19:16
 */
public class NonBlockingIO {
}

//客户端
class client {
    public static void main(String[] args) throws IOException {
        //1,获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //2.切换为非阻塞模式
        sChannel.configureBlocking(false);
        //3.分配指定大小缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            //4.发送数据给服务器端
            String str = scanner.next();
            buf.put((new Date().toString() +"\n" + str).getBytes());
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }


        //5，关闭通道
        sChannel.close();
    }
}

//服务器端
class server {
    public static void main(String[] args) throws IOException {
        //1.获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2.切换为非阻塞模式
        ssChannel.configureBlocking(false);
        //3.绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        //4.获取选择器
        Selector selector = Selector.open();
        //5.将通道注册到选择器上
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6.轮询获取选择器上已经“准备就绪的事件
        while (selector.select() > 0) {
            //7.获取当前选择器中所有注册的“选择键（以就绪的监听事件）”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();
            while (it.hasNext()) {
                //8.获取准备就绪的事件
                SelectionKey sk = it.next();

                //9.判断具体时间什么事件准备就绪
                if (sk.isAcceptable()) {
                    //10.若”接收就绪“，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();
                    //11.切换为非阻塞模式
                    sChannel.configureBlocking(false);
                    //12.将通道注册到选择器上
                    sChannel.register(selector,SelectionKey.OP_READ);
                } else if (sk.isReadable()) {
                    //13.获取选择器上读就绪状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //14.读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(),0,len));
                        buf.clear();
                    }
                }
                //15.取消选择键 SelectionKey
                it.remove();
            }
        }
    }
}