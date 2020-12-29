package org.design.adapter.classadapter;

/**
 * 类适配器
 * @author lijichen
 * @date 2020/8/13 - 19:43
 */
public class Cline {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.charging(new AdapterVoltage());
    }
}
class Voltage220V {
    private int voltage = 220;

    public int outputVoltage220V(){
        System.out.println("电压是：" + voltage);
        return voltage;
    }
}
interface Voltage5V {
    int outputVoltage5V();
}
class AdapterVoltage extends Voltage220V implements Voltage5V {

    @Override
    public int outputVoltage5V() {
        int voltage = super.outputVoltage220V();
        System.out.println("已经转换为5V！");
        return voltage/44;
    }
}
class Phone{
    //充电
    public void charging(Voltage5V voltage5V){
        if (voltage5V.outputVoltage5V() == 5){
            System.out.println("正在充电。。。");
        }
    }
}