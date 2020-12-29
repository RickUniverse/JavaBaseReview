package org.jdk14.records;

import javax.naming.Name;

public record RecoreTestClass(String name, String pass, int age) {
    public static int i;
    public RecoreTestClass(int i){
        this(null,null,i);
    }
    public static int show(){
        System.out.println("dddd");
        return i = 123;
    }
}
class Test {//不能继承RecoreTestClass
//    public Test(String name, String pass, int age) {
//        super(name, pass, age);
//    }

    public static void main(String[] args) {
        RecoreTestClass recoreTestClass = new RecoreTestClass("qwe", "eee", 123);
        recoreTestClass.name();
        System.out.println(RecoreTestClass.show());
        System.out.println(recoreTestClass);
        String str = """
                asdfasdfasdf
                asdf
                asdf
                asdfa
                sdf
                awe
                f
                wef
                wae
                f
                awefaw
                f
                
                a
                """;
    }
}