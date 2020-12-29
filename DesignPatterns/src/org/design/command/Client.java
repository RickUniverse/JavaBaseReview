package org.design.command;

/**
 * 命令设计模式
 * @author lijichen
 * @date 2020/8/16 - 17:42
 */
public class Client {
    public static void main(String[] args) {
        //创建灯对象
        LightReceiver lightReceiver = new LightReceiver();
        //创建灯的开关命令
        LightOnCommand lightOnCommand = new LightOnCommand(lightReceiver);
        LightOffCommand lightOffCommand = new LightOffCommand(lightReceiver);
        //创建遥控器实例
        RemoteController remoteController = new RemoteController();
        //传入命令
        remoteController.setCommand(0,lightOnCommand,lightOffCommand);
        //调用命令
        remoteController.onButtonWasPushed(0);
        remoteController.offButtonWasPushed(0);
        remoteController.undoButtonWasPushed();
    }
}
//命令接口，封装执行和撤销功能
interface Command {
    void execute();
    void undo();
}
//灯的打开命令的具体类
class LightOnCommand implements Command {

    //聚合接受者：灯
    private LightReceiver lightReceiver;

    //构造器：传入接受者
    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.on();
    }

    //撤销表示关闭
    @Override
    public void undo() {
        lightReceiver.off();
    }
}
//灯的关闭命令的具体类
class LightOffCommand implements Command {

    //聚合接受者：灯
    private LightReceiver lightReceiver;

    //构造器：传入接受者
    public LightOffCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.off();
    }

    //撤销表示关闭
    @Override
    public void undo() {
        lightReceiver.on();
    }
}
//空命令，遥控器初始化赋值使用
class NoCommand implements Command {

    @Override
    public void execute() {
    }

    //撤销表示关闭
    @Override
    public void undo() {
    }
}
//接受者：receiver
class LightReceiver {
    public void on (){
        System.out.println("灯打开了");
    }
    public void off (){
        System.out.println("灯关闭了");
    }
}
//指挥者，遥控器
class RemoteController {
    //聚合命令数组
    Command[] onCommand;
    Command[] offCommand;
    //记录上一次的操作，撤销用
    Command undoCommand;
    //空命令
    NoCommand noCommand;

    //构造器初始化按钮
    public RemoteController() {
        onCommand = new Command[5];
        offCommand = new Command[5];
        //空命令
        noCommand = new NoCommand();
        for (int i = 0; i < onCommand.length ; i++) {
            onCommand[i] = noCommand;
            offCommand[i] = noCommand;
        }
    }

    //设置命令
    public void setCommand (int len,Command on,Command off){
        onCommand[len] = on;
        offCommand[len] = off;
    }

    //按下开机按钮
    public void onButtonWasPushed(int len){
        onCommand[len].execute();
        //记录这次按下的按钮用作撤销
        undoCommand = onCommand[len];
    }

    //按下关机按钮
    public void offButtonWasPushed(int len){
        offCommand[len].execute();
        undoCommand = offCommand[len];
    }

    //按下撤销按钮
    public void undoButtonWasPushed(){
        undoCommand.undo();
    }
}