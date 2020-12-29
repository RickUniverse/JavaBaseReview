package org.design.state;

import java.util.Random;

/**
 * 状态模式
 * @author lijichen
 * @date 2020/8/18 - 17:27
 */
public class Client {
    public static void main(String[] args) {
        //开始抽奖
        //创建活动类
        RaffleActivity raffleActivity = new RaffleActivity(1);
        //抽奖
        for (int i = 0; i < 30; i++) {
            System.out.println("___________("+(i+1)+")次抽奖______________");
            //先扣积分
            raffleActivity.debuctMoney();
            //然后抽奖
            raffleActivity.raffle();
        }
        System.out.println();
    }
}
//状态抽象类
abstract class State {
    public abstract void deduceMoney();//扣除积分的方法
    public abstract boolean raffler();//抽奖状态
    public abstract void dispensePrize();//分配奖品
}
//具体状态类
class NoRaffleState extends State {
    //聚合一个活动类
    private RaffleActivity raffleActivity;

    public NoRaffleState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    @Override
    public void deduceMoney() {
        System.out.println("扣除10积分可以开始抽奖了！");
        raffleActivity.setState(raffleActivity.getCanRaffleState());//将状态设置为可抽奖
    }

    @Override
    public boolean raffler() {
        System.out.println("扣了积分才可以抽奖");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品！");
    }
}
//
class CanRaffleState extends State {
    //聚合一个活动类
    private RaffleActivity raffleActivity;

    public CanRaffleState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }
    @Override
    public void deduceMoney() {
        System.out.println("可以抽奖了！");
    }

    @Override
    public boolean raffler() {
        System.out.println("正在抽奖！");
        Random random = new Random();
        int num = random.nextInt(10);//百分之10中奖概率
        if (num == 0) {
            System.out.println("恭喜中奖了！");
            raffleActivity.setState(raffleActivity.getDispenseState());//改变状态为发放奖品
            return true;
        } else {
            System.out.println("很抱歉，没有中奖！");
            raffleActivity.setState(raffleActivity.getNoRaffleState());//修改状态为不能抽奖
            return false;
        }
    }

    @Override
    public void dispensePrize() {
        System.out.println("没有中奖");
    }
}
//发送奖品的状态
class DispenseState extends State {
    //聚合一个活动类
    private RaffleActivity raffleActivity;

    public DispenseState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }
    @Override
    public void deduceMoney() {
        System.out.println("不需要扣除积分");
    }

    @Override
    public boolean raffler() {
        System.out.println("不能抽奖！");
        return false;
    }

    @Override
    public void dispensePrize() {
        if (raffleActivity.getCount() > 0){
            System.out.println("奖品已经发放完成");
            raffleActivity.setState(raffleActivity.getNoRaffleState());//状态改为不可抽奖
        } else {
            System.out.println("奖品发完了！");
            //该状态为发送完毕
            raffleActivity.setState(raffleActivity.getDisoenseOutState());//奖品发完的状态
            //活动结束
            System.out.println("活动结束！");
            System.exit(0);
        }
    }
}
//奖品发完的状态类
class DisoenseOutState extends State {
    //聚合一个活动类
    private RaffleActivity raffleActivity;

    public DisoenseOutState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }
    @Override
    public void deduceMoney() {
        System.out.println("奖品已将发完");
    }

    @Override
    public boolean raffler() {
        System.out.println("奖品已将发完");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品已将发完");
    }
}
//活动类
class RaffleActivity {
    //维护当前状态
    State state;
    //奖品数量
    int count;
    //维护四个状态
    State noRaffleState = new NoRaffleState(this);
    State disoenseOutState = new DisoenseOutState(this);
    State dispenseState = new DispenseState(this);
    State canRaffleState = new CanRaffleState(this);

    //构造器
    public RaffleActivity(int count) {
        //初始化当前状态为不可抽象状态
        this.state = getNoRaffleState();
        this.count = count;
    }
    //扣分
    public void debuctMoney(){
        state.deduceMoney();//因为默认是不能抽奖所以需要扣分
    }
    //抽奖
    public void raffle(){
        if (state.raffler()) {
            //如果为true表示中奖了
            state.dispensePrize();//领取奖品
        }
    }


    public int getCount() {
        return count--;//抽到奖品后 商品数量--
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getNoRaffleState() {
        return noRaffleState;
    }

    public void setNoRaffleState(State noRaffleState) {
        this.noRaffleState = noRaffleState;
    }

    public State getDisoenseOutState() {
        return disoenseOutState;
    }

    public void setDisoenseOutState(State disoenseOutState) {
        this.disoenseOutState = disoenseOutState;
    }

    public State getDispenseState() {
        return dispenseState;
    }

    public void setDispenseState(State dispenseState) {
        this.dispenseState = dispenseState;
    }

    public State getCanRaffleState() {
        return canRaffleState;
    }

    public void setCanRaffleState(State canRaffleState) {
        this.canRaffleState = canRaffleState;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}