package org.jdk9.interfaces;

public interface NewInterface {
    //下列方法的修饰符都是public
    void method();

    static void methodStatic(){
        System.out.println("static method");
    }

    default void methodDefault(){
        System.out.println("default method");
        methodPrivate();
    }

    private void methodPrivate(){
        System.out.println("private method");
    }
}
class Test implements NewInterface{

    @Override
    public void method() {

    }

//    @Override
//    public void methodDefault() {
//
//    }

    public static void main(String[] args) {
        NewInterface.methodStatic();
    }
}