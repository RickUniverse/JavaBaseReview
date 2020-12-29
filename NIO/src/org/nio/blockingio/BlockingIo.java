package org.nio.blockingio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 阻塞式IO
 * @author lijichen
 * @date 2020/10/3 - 18:29
 */
public class BlockingIo {

}

class client {
    public static void main(String[] args) throws IOException {
        //获取通道
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        //读取图片通道
        FileChannel inChannel = FileChannel.open(Paths.get("other\\1.png"), StandardOpenOption.READ);
        //缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        //给服务器端发送数据
        while (inChannel.read(buf) != -1) {
            buf.flip();
            //传输
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.shutdownOutput();

        //接收服务器端的反馈
        int len = 0;
        while ((len = sChannel.read(buf)) != -1) {
            buf.flip();
            //输出服务器端的反馈
            System.out.println(new String(buf.array(),0,len));
            buf.clear();
        }

        inChannel.close();
        sChannel.close();
    }
}

//服务器端
class server {
    public static void main(String[] args) throws IOException {


        //服务器获取连接
        ServerSocketChannel ssChannel = ServerSocketChannel.open();

        //绑定客户端
        ssChannel.bind(new InetSocketAddress(9898));

        //接收请求
        SocketChannel sChannel = ssChannel.accept();

        //输出缓冲流
        ByteBuffer buf = ByteBuffer.allocate(1024);

        //out管道
        FileChannel fChannel = FileChannel.open(Paths.get("other\\2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //放入服务器
        while (sChannel.read(buf) != -1) {
            buf.flip();
            //保存到本地
            fChannel.write(buf);
            buf.clear();
        }

        //向客户端发送数据
        buf.put("已经收到！".getBytes());
        buf.flip();
        sChannel.write(buf);

        fChannel.close();
        sChannel.close();
        ssChannel.close();
    }
}