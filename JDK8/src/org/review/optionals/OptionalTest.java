package org.review.optionals;

import java.util.Optional;

/**
 * @author lijichen
 * @date 2020/8/8 - 18:18
 */
public class OptionalTest {
    public static void main(String[] args) {
        Girl girl = new Girl();
        girl = null;
        //of(T t):参数不能是null
        //Optional<Girl> girl1 = Optional.of(girl);
        //ofNullable(T t):参数可以是null
        Optional<Girl> girl2 = Optional.ofNullable(girl);
        System.out.println(girl2);
        System.out.println("*************************");
        Boy boy = new Boy();
        System.out.println(getGirlName(boy));//可以保证不报错
    }
    static String getGirlName(Boy boy){
        Optional<Boy> boy1 = Optional.ofNullable(boy);
        Boy sss = boy1.orElse(new Boy(new Girl("sss"), 123));//如果是null则返回为new Boy(new Girl("sss"),123)
        Girl girl = sss.getGirl();
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        Girl girl2 = girl1.orElse(new Girl("werw"));//判斷boy里是否有girl，没有则返回new Girl("werw")
        return girl2.getAnme();
    }
}
class Girl{
    private String anme;

    public Girl() {
    }

    @Override
    public String toString() {
        return "Girl{" +
                "anme='" + anme + '\'' +
                '}';
    }

    public String getAnme() {
        return anme;
    }

    public void setAnme(String anme) {
        this.anme = anme;
    }

    public Girl(String anme) {
        this.anme = anme;
    }
}
class Boy{
    private Girl girl;

    public Boy(Girl girl, int age) {
        this.girl = girl;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                ", age=" + age +
                '}';
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boy(int age) {
        this.age = age;
    }

    public Boy() {
    }
}
