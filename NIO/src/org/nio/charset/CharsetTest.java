package org.nio.charset;


import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 字符集
 * @author lijichen
 * @date 2020/10/3 - 17:30
 */
public class CharsetTest {
    public static void main(String[] args) throws CharacterCodingException {
        //使用GBK进行编码
        Charset gbk = Charset.forName("GBK");

        //获取编码器
        CharsetEncoder encoder = gbk.newEncoder();
        //获取解码器
        CharsetDecoder decoder = gbk.newDecoder();

        //缓冲流
        CharBuffer charBuffer = CharBuffer.allocate(1024);

        //写数据
        charBuffer.put("你好，hello");

        //读
        charBuffer.flip();
        //编码
        ByteBuffer bBuf = encoder.encode(charBuffer);
        for (int i = 0; i < 11; i++) {
            System.out.println(bBuf.get());
        }

        //解码
        charBuffer.flip();
        CharBuffer decode = decoder.decode(bBuf);
//        System.out.println(decode.toString());

        System.out.println("_________________________");

        //其他
        Charset utf = Charset.forName("UTF-8");
        charBuffer.flip();
        System.out.println(utf.decode(bBuf).toString());
    }
}
