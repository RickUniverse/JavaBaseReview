package org.jdk14.instanceofs;

/**
 * @author lijichen
 * @date 2020/8/9 - 21:29
 */
public class InstanceofTest {
    public static void main(String[] args) {
        Object obj = new String("hello");

        System.out.println(obj instanceof String str && str.length() != 0);

        if (obj instanceof String str){
            System.out.println(str);
        }else{
            //System.out.println(str);
        }
    }
    static void show(){
        Object obj = new String("hello");

        if (obj instanceof String str){
            System.out.println(str);
        }else{
            //System.out.println(str);
        }
    }
}
