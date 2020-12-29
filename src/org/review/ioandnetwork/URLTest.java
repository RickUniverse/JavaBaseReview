package org.review.ioandnetwork;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author lijichen
 * @date 2020/8/5 - 20:14
 */
public class URLTest {
    public static void main(String[] args) {
       /* try {
            URL url = new URL("http://localhost:8080/huawei/123.html?user=123");
            System.out.println(url.getPath());
            System.out.println(url.getFile());//。。。。
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/

        HttpURLConnection urlConnection = null;
        InputStream input = null;
        FileOutputStream outPut = null;
        //String strUrl = " https://res5.vmallres.com/shopdc/pic/97ad9844-8e36-4123-b830-b37d526c66ca.jpg";
        String strUrl = "https://jx.123ku3.com/123kudpbfq/?url=https://www.zhuticlub.com:65/20191018/WBlGgzLh/index.m3u8";
        try {
            URL url = new URL(strUrl);//"http://127.0.0.1:8080/examples/1.jpg"

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();//建立连接

            input = urlConnection.getInputStream();
            outPut = new FileOutputStream("baidu.m3u8");

            byte[] bytes = new byte[1024];
            int len;
            while (((len = input.read(bytes)) != -1)) {
                outPut.write(bytes,0,len);
            }
            System.out.println("完成！");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outPut != null) {
                try {
                    outPut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
    }
}
