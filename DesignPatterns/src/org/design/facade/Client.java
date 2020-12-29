package org.design.facade;

/**
 *外观设计模式
 *
 * @author lijichen
 * @date 2020/8/15 - 16:38
 */
public class Client {
    public static void main(String[] args) {
        //使用外观类开始流程
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.player();
        homeTheaterFacade.pause();
        homeTheaterFacade.end();
    }
}
//具体类
//使用单例封装
//DVD类
class DVDPlayer {
    private static DVDPlayer instance = new DVDPlayer();
    public static DVDPlayer getDvdPlayerInstance(){
        return instance;
    }

    public void open(){
        System.out.println("dvd open");
    }
    public void close(){
        System.out.println("dvd close");
    }
}
//具体类
//使用单例封装
//爆米花类
class Popcorn {
    private static Popcorn instance = new Popcorn();
    public static Popcorn getPopcornInstance(){
        return instance;
    }

    public void open(){
        System.out.println("Popcorn open");
    }
    public void close(){
        System.out.println("Popcorn close");
    }
}
//具体类
//使用单例封装
//投影仪类
class Projector {
    private static Projector instance = new Projector();
    public static Projector getProjectorInstance(){
        return instance;
    }

    public void open(){
        System.out.println("Projector open");
    }
    public void close(){
        System.out.println("Projector close");
    }
}
//具体类
//使用单例封装
//屏幕类
class Screen {
    private static Screen instance = new Screen();
    public static Screen getScreenInstance(){
        return instance;
    }

    public void up(){
        System.out.println("Screen up");
    }
    public void down(){
        System.out.println("Screen down");
    }
}
//具体类
//使用单例封装
//剧院灯光类
class TheaterLight {
    private static TheaterLight instance = new TheaterLight();
    public static TheaterLight getTheaterLightInstance(){
        return instance;
    }

    public void dim(){
        System.out.println("TheaterLight dim");
    }
    public void bright(){
        System.out.println("TheaterLight bright");
    }
}
//外观类
//封装具体物品的功能
//
class HomeTheaterFacade {
    //聚合
    DVDPlayer dvdPlayer;
    Popcorn popcorn;
    Projector projector;
    Screen screen;
    TheaterLight theaterLight;
    //构造器实例化
    public HomeTheaterFacade() {
        this.dvdPlayer = DVDPlayer.getDvdPlayerInstance();
        this.popcorn = Popcorn.getPopcornInstance();
        this.projector = Projector.getProjectorInstance();
        this.screen = Screen.getScreenInstance();
        this.theaterLight = TheaterLight.getTheaterLightInstance();
    }
    //开始准备方法
    public void ready(){
        popcorn.open();
        dvdPlayer.open();
        screen.down();
        theaterLight.dim();
    }
    //开始播放
    public void player() {
        projector.open();
    }
    //暂停
    public void pause() {
        projector.close();
    }
    //结束
    public void end() {
        projector.close();
        popcorn.close();
        dvdPlayer.close();
        screen.up();
        theaterLight.bright();
    }
}