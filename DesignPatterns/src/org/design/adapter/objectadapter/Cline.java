package org.design.adapter.objectadapter;

/**
 * 对象适配器
 *
 * @author lijichen
 * @date 2020/8/13 - 20:26
 */
public class Cline {
    public static void main(String[] args) {
        Phone p = new Phone();
        p.charging(new AdapterVoltage(new Voltage220V()));
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
class AdapterVoltage implements Voltage5V {

    private Voltage220V voltage220V;

    public AdapterVoltage(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int outputVoltage5V() {
        int voltage = 0;
        if (voltage220V != null) {
            voltage = voltage220V.outputVoltage220V() / 44;
            System.out.println("正在转换电压！！");
        }
        return voltage;
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
