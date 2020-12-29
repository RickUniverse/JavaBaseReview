package org.review.io;

import java.io.File;
import java.io.IOException;

/**
 * @author lijichen
 * @date 2020/8/3 - 19:38
 */
public class FileClassTest {
    public static void main(String[] args) throws IOException {
        File file1 = new File("123.txt");
        File file2 = new File("D:"+File.separator+"123.txt");

        File file3 = new File("D:\\qqq","123.txt");
        File file4 = new File(file3,"123.txt");
        System.out.println("****************************");
        File file5 = new File("123.txt");
        System.out.println(file5.getAbsolutePath());//绝对路径
        System.out.println(file5.getPath());//  获取路径
        System.out.println(file5.getName());//文件名
        System.out.println(file5.getParent());//上层目录
        System.out.println(file5.length());//文件字节长度
        System.out.println(file5.lastModified());//最后一次修改时间

        File file6 = new File("D:\\");
        System.out.println(file6.getAbsolutePath());//绝对路径
        System.out.println(file6.getPath());//  获取路径
        System.out.println(file6.getName());//文件名
        System.out.println(file6.getParent());//上层目录
        System.out.println(file6.length());//文件字节长度
        System.out.println(file6.lastModified());//最后一次修改时间

        System.out.println("*****************");

        for (String s : file6.list()) {//获取所有文件名
            System.out.println(s);
        }
        for (File file : file6.listFiles()) {
            System.out.println(file);
        }
        System.out.println("*****************");
        System.out.println(file1.renameTo(new File("D:\\123.ttt")));
        System.out.println("*****************");
        File file = new File("aaa");
        System.out.println(file.isDirectory());//是否是目录
        System.out.println(file.isFile());//是否是文件
        System.out.println(file.canRead());//是否可读
        System.out.println(file.canWrite());//是否可写
        System.out.println(file.isHidden());//是否隐藏

        if (!file.exists()) {
            file.mkdir();//创建目录，如果上层没有存在或存在相同名字的目录就不创建
//            file.mkdirs();//创建目录，如果上层目录不存在也一并创建
//            file.createNewFile();//创建文件
        }else{
            file.delete();
        }
    }
}
