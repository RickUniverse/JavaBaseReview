package org.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 通道作用
 * @author lijichen
 * @date 2020/10/3 - 16:25
 */
public class ChannelTest {
    public static void main(String[] args) {
        RandomAccessFile raf1 = null;
        FileChannel channel1 = null;
        RandomAccessFile raf2 = null;
        FileChannel raf2Channel = null;


        try {
            //分散写入，和聚集写出
            raf1 = new RandomAccessFile("other\\111.txt","rw");

            //1获取通道
            channel1 = raf1.getChannel();

            //2分配指定大小的缓冲区
            ByteBuffer bb1 = ByteBuffer.allocate(100);
            ByteBuffer bb2 = ByteBuffer.allocate(1024);

            //3分散读取
            ByteBuffer[] bufs = {bb1, bb2};
            channel1.read(bufs);

            for (ByteBuffer buf : bufs) {
                //开启读入数据
                buf.flip();
            }
            System.out.println(new String(bufs[0].array(),0,bufs[0].limit()));
            System.out.println("++++++++++++++++++++++++++");
            System.out.println(new String(bufs[1].array(),0,bufs[1].limit()));

            //4聚集写入
            raf2 = new RandomAccessFile("other\\222.txt", "rw");
            //获取通道
            raf2Channel = raf2.getChannel();

            raf2Channel.write(bufs);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf2Channel != null) {
                try {
                    raf2Channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf2 != null) {
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(channel1 != null) {
                try {
                    channel1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf1 != null) {
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


//        direct2();
//        direct();
//        noDirect();

    }

    private static void direct2() {
        //通道之间的直接传输
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            //直接缓存
            inChannel = FileChannel.open(Paths.get("other\\1.png"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("other\\2.png"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);

            inChannel.transferTo(0,inChannel.size(),outChannel);
//            outChannel.transferFrom(inChannel,0,inChannel.size());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void direct() {
        //使用直接缓冲区完成文件的复制（内存映射文件）
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            //获取读通道
            inChannel = FileChannel.open(Paths.get("other\\1.png"), StandardOpenOption.READ);
            outChannel = FileChannel.open(Paths.get("other\\4.png"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

            //内存映射文件
            //直接缓冲区，物理内存
            //只读直接缓冲区
            MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
            //读写直接缓冲区
            MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE,0, inChannel.size());
            //直接对缓冲区读写操作
            byte[] dst = new byte[inMappedBuf.limit()];
            //读
            inMappedBuf.get(dst);
            //写
            outMappedBuf.put(dst);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void noDirect() {
        //利用通道完成文件的复制
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream("other\\1.png");
            fos = new FileOutputStream("other\\4.png");

            //获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            //分配指定大小缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);

            //while,将通道中的数据放入缓冲区中
            while (inChannel.read(buf) != -1) {
                //开始写
                buf.flip();
                //将缓冲区中的数据写入通道
                outChannel.write(buf);
                //写一次后清空
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
