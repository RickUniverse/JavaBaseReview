package org.review.innerclass;

/**
 * @author lijichen
 * @date 2020/7/26 - 20:10
 */
public class InnerClass {
    public static void main(String[] args) {
        Persion.Dog persion = new Persion.Dog();//对于静态的来说
        //Persion p = new Persion();
        Persion.Bird pdog =new Persion().new Bird();
        new Persion().new Bird().Fly("飞人");
    }
}
class Persion{
    private String name;
    static class Dog{
        private String name;

        public void eat(){
            System.out.println("狗吃");
        }
    }
    class Bird{
        private String name;

        Persion.Dog pdog = new Persion.Dog();


        public void Fly(String name){
            System.out.println("飞");
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(Persion.this.name);
        }
    }
    private Comparable getComparable(){
        /*return new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };*/
        class MyComparable implements Comparable{

            @Override
            public int compareTo(Object o) {
                return 0;
            }
        }
        return new MyComparable();
    }
   /* private Persion(){
        class Dog{

        }
    }*/
    {
        class Dog{

        }
    }
}
