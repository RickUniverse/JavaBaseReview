package org.design.principle.inversion;

/**
 * @author lijichen
 * @date 2020/8/10 - 19:48
 */

class Test{
    public static void main(String[] args) {
        TV tv = new TV();
        //通过接口传递实现依赖
        OpenAndClose openAndClose = new OpenAndClose();
        openAndClose.Open(tv);
        //通过构造器传递实现依赖
        TV2 tv2 = new TV2();
        OpenAndClose2 op = new OpenAndClose2(tv2);
        op.Open();
        //通过通过构setter递实现依赖
        TV3 tv3 = new TV3();
        OpenOrClose3 op3 = new OpenOrClose3();
        op3.setItv3(tv3);
        op3.Open();
    }
}
//通过接口传递实现依赖
interface IOpernAndClose{
    void Open(ITV itv);
}
interface ITV{
    void Play();
}
class TV implements ITV{

    @Override
    public void Play() {
        System.out.println("电视已打开！");
    }
}
class OpenAndClose implements IOpernAndClose{

    @Override
    public void Open(ITV itv) {
        itv.Play();
    }
}
//2，通过构造方法传递依赖
interface IOpenOrClose{
    void Open();
}
interface ITV2{
    void Play();
}
class TV2 implements ITV2{

    @Override
    public void Play() {
        System.out.println("电视机通过构造方法传递依赖打开了！");
    }
}
class OpenAndClose2 implements IOpenOrClose{
    private ITV2 itv2;

    public OpenAndClose2(ITV2 itv2) {
        this.itv2 = itv2;
    }

    @Override
    public void Open() {
        itv2.Play();
    }
}
//通过setter传递依赖
interface IOpenOrClose3 {
    void Open();
}
interface ITV3 {
    void Play();
}
class TV3 implements ITV3 {

    @Override
    public void Play() {
        System.out.println("通过setter");
    }
}
class OpenOrClose3 implements IOpenOrClose3{
    private ITV3 itv3;

    public void setItv3(ITV3 itv3) {
        this.itv3 = itv3;
    }

    @Override
    public void Open() {
        itv3.Play();
    }
}