package org.review.reflections;

import java.util.Random;

/**
 * @author lijichen
 * @date 2020/8/6 - 17:41
 */
public class ReflectionTest2 {
    public static void main(String[] args) {
        String classPath = "";
        for (int i = 0; i < 100; i++) {
            int random = new Random().nextInt(3);
            switch (random){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "org.review.reflections.Persion";
                    break;
                default:
                    System.out.println("错误！");
                    break;
            }
            Object instance = null;
            try {
                instance = getInstance(classPath);
                System.out.println(instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
    static Object getInstance(String classPath) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return Class.forName(classPath).newInstance();
    }
}
