package org.design.singleton.type1;

/**
 * @author lijichen
 * @date 2020/8/11 - 19:10
 */
public class SingletonTest01 {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2);
        //使用枚举
        Singleton2 instance4 = Singleton2.INSTANCE;
        Singleton2 instance5 = Singleton2.INSTANCE;
        System.out.println(instance4 == instance5);
        System.out.println(instance4.hashCode());
        System.out.println(instance5.hashCode());
    }
}
//饿汉式（静态变量）
//class Singleton{
//    private Singleton(){}
//
//    private final static Singleton instance = new Singleton();
//
//    public static Singleton getInstance(){
//        return instance;
//    }
//}
//饿汉式（静态代码块）
//class Singleton{
//    private Singleton(){}
//
//    static {
//        instance = new Singleton();
//    }
//
//    private static Singleton instance;
//
//    public static Singleton getInstance(){
//        return instance;
//    }
//}
//双重检查，线程安全，效率高，推荐使用
//class Singleton{
//    private Singleton(){}
//
//    private static volatile Singleton instance;//volatile:进行修改后快速保存修改后的内容
//
//    public static Singleton getInstance(){
//        if (instance == null){
//            synchronized (Singleton.class){
//                if (instance == null){
//                    instance = new Singleton();
//                }
//            }
//        }
//        return instance;
//    }
//}
//静态内部类，推荐
class Singleton{
    private Singleton(){}

    private static class SingletonInstance{
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
//枚举，推荐使用
enum Singleton2{
    INSTANCE;
    public void say(){
        System.out.println("hi!!");
    }
}