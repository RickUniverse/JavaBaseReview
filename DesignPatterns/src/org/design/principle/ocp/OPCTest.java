package org.design.principle.ocp;

/**
 * @author lijichen
 * @date 2020/8/11 - 15:45
 */
//满足开闭原则，使用方尽量不做修改
public class OPCTest {
    public static void main(String[] args) {
        GraphicEditor g = new GraphicEditor();
        g.drowShape(new Rectangle());
        g.drowShape(new Circle());
    }
}
//使用方
class GraphicEditor{
    public void drowShape(Shape s){
        s.draw();
    }
}

//画图抽象基类
abstract class Shape{
    int s_Type;
    void draw(){
        System.out.println("画画");
    }
}
//画矩形
class Rectangle extends Shape {
    public Rectangle() {
        super.s_Type = 1;
    }

    @Override
    void draw() {
        System.out.println("画矩形");
    }
}
class Circle extends Shape {
    public Circle() {
        super.s_Type = 2;
    }

    @Override
    void draw() {
        System.out.println("画圆形");
    }
}