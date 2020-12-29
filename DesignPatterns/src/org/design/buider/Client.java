package org.design.buider;

/**
 * 建造者模式
 *
 * @author lijichen
 * @date 2020/8/13 - 18:21
 */
public class Client {
    public static void main(String[] args) {
        //建造房子的类型
        CommonHouse commonHouse = new CommonHouse();
        //指挥者
        BuilderDirector builderDirector = new BuilderDirector(commonHouse);
        builderDirector.construct("小茅屋 ");
        //高楼
        HightHouse hightHouse = new HightHouse();
        BuilderDirector builderDirector2 = new BuilderDirector(hightHouse);
        builderDirector2.construct("超级高楼");
    }
}
//房子---》具体产品product
class House{
    private String name;

    public House() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
//抽象建造者--》builder
abstract class BuilderHouse {
    protected House house = new House();
    //抽象方法，让具体的子类实现
    public abstract void buildBisic();
    public abstract void buildWalls();
    public abstract void roofed();
    public House build(String houseName){
        house.setName(houseName);
        return house;
    }
}
//具体建造者--》concreteBuilder
//普通房子
class CommonHouse extends BuilderHouse {

    @Override
    public void buildBisic() {
        System.out.println(super.house.getName() + "是普通房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println(super.house.getName() + "是普通房子盖墙");
    }

    @Override
    public void roofed() {
        System.out.println(super.house.getName() + "是普通房子封顶");
    }
}
//高楼
class HightHouse extends BuilderHouse {

    @Override
    public void buildBisic() {
        System.out.println(super.house.getName() + "是高楼房子打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println(super.house.getName() + "是高楼房子盖墙");
    }

    @Override
    public void roofed() {
        System.out.println(super.house.getName() + "是高楼房子封顶");
    }
}
//指挥者--》Director
class BuilderDirector{
    BuilderHouse builderHouse;

    public BuilderDirector(BuilderHouse builderHouse) {
        this.builderHouse = builderHouse;
    }
    //修改要建造的房子种类
    public void setBuilderHouse(BuilderHouse builderHouse) {
        this.builderHouse = builderHouse;
    }
    public House construct(String houseName){
        House build = builderHouse.build(houseName);
        builderHouse.buildBisic();
        builderHouse.buildWalls();
        builderHouse.roofed();
        return build;
    }
}