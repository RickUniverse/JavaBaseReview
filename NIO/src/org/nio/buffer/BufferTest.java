package org.nio.buffer;

import java.nio.ByteBuffer;

/**
 * 缓冲区
 * @author lijichen
 * @date 2020/10/3 - 14:38
 */
public class BufferTest {
    public static void main(String[] args) {
        //直接缓冲区

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        //判断是不是直接缓冲区
        System.out.println(byteBuffer.isDirect());

//        Buffer();

    }

    private static void Buffer() {
        //数据
        String str = "abcde";

        //分配一个缓冲流
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //放入数据
        byteBuffer.put(str.getBytes());
        /*
        * 参数
        * */
        System.out.println("------------put---------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //读数据模式
        byteBuffer.flip();
        System.out.println("------------flip---------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //读取数据
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes,0,3);

        System.out.println("------------get---------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        System.out.println(new String(bytes));

        //可重复读
        byteBuffer.rewind();

        System.out.println("------------rewind---------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //标记
        byteBuffer.get(bytes,0,2);
        System.out.println(new String(bytes,0,2));

        byteBuffer.mark();

        System.out.println("------------mark before---------------");
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        byteBuffer.get(bytes,2,2);

        System.out.println(new String(bytes,2,2));

        System.out.println("------------reset after---------------");

        byteBuffer.reset();

        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.capacity());

        //清空缓冲区
        byteBuffer.clear();
    }
}
