package org.review.maps;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author lijichen
 * @date 2020/8/2 - 15:49
 */
public class PropertiesTest {
    public static void main(String[] args) {
        FileInputStream file = null;
        try {
            Properties prit = new Properties();
            file = new FileInputStream("jdbc.properties");
            prit.load(file);
            String url = prit.getProperty("url");
            String Driver = prit.getProperty("Driver");
            String user = prit.getProperty("user");
            System.out.println(url+"--"+Driver+"---"+user);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (file != null){
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
