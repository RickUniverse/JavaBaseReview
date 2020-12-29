package org.review.reflections;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author lijichen
 * @date 2020/8/6 - 17:12
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws IOException {
        Properties pro = new Properties();

        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("jdbc.properties");

        pro.load(inputStream);

        String name = pro.getProperty("name");
        System.out.println(name);
    }
}
