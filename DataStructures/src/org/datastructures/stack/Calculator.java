package org.datastructures.stack;

/**
 * 中缀表达式
 * @author lijichen
 * @date 2020/8/23 - 17:00
 */
public class Calculator {
    public static void main(String[] args) {
        //创建表达式
        String expression = "300+2*6-2";
        //创建两个栈
        ArrayStack numStark = new ArrayStack(20);
        ArrayStack operStark = new ArrayStack(20);
        //定义计算需要的变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始循环操作
        while (true) {
            //依次的到表达式expression的值
            ch = expression.substring(index,index + 1).charAt(0);
            //判断ch是什么
            if (operStark.isOper(ch)) {//如果是运算符
                //判断当前的操作符符号栈是否为空
                if (!operStark.isEmpty()) {
                    //不为空的操作
                    //比较操作符，跟栈中的操作符进行比较
                    if (operStark.priority(ch) <= operStark.priority(operStark.peek())) {//如果当前的操作符的优先级大于栈中的优先级
                        //则进行取出进行运算
                        num1 = numStark.pop();
                        num2 = numStark.pop();
                        oper = operStark.pop();//需要从栈中pop出栈一个操作符
                        //运算
                        res = operStark.cla(num1, num2, (char)oper);
                        //得到的结果入数栈
                        numStark.push(res);
                        //当前符号入操作符栈
                        operStark.push(ch);
                    } else {
                        //当前操作符优先级大于栈中符号
                        //直接入站
                        operStark.push(ch);
                    }
                } else {
                    //为空的操作
                    //直接入栈
                    operStark.push(ch);
                }
            } else {
                //如果不是运算符
                //直接入数栈
                //numStark.push(ch - 48);//因为ascall中1，对应的是49
                /*
                * 改进
                * */
                keepNum += ch;//当前数字拼接到keepNum
                //如果ch已经是最后一个
                if (index == expression.length() - 1) {
                    numStark.push(Integer.parseInt(keepNum));
                } else {
                    //判断下一个是不是数字
                    if (operStark.isOper(expression.substring(index+1,index+2).charAt(0))) {
                        //如果是运算符，就入栈，不需要进行后续操作
                        numStark.push(Integer.parseInt(keepNum));//入栈
                        keepNum = "";//将keepnum置空
                    }
                }

            }
            //index + 1 进行下一次的 操作
            index++;
            //判断是否到扫描到最后
            if (index >= expression.length()) {
                break;
            }
        }
        //循环运算栈中剩余的
        while (true) {
            if (operStark.isEmpty()) {//符号栈为空表示计算完毕
                break;
            }
            //表达式扫描完毕
            num1 = numStark.pop();
            num2 = numStark.pop();
            oper = operStark.pop();//需要从栈中pop出栈一个操作符
            //运算
            res = operStark.cla(num1, num2, (char)oper);
            numStark.push(res);
        }
        System.out.printf("结果为%s = %d",expression,numStark.pop());
    }
}
class ArrayStack {
    //最大栈空间
    public int maxSize;
    //栈顶
    public int top = -1;
    //栈数组
    public int[] stack;

    //构造器
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        //初始化栈数组
        stack = new int[this.maxSize];
    }
    //判断栈是否满
    public boolean isFall() {
        return top == this.maxSize - 1;
    }
    //判断栈是否空
    public boolean isEmpty() {
        return top == -1;
    }
    //入栈
    public void push(int value) {
        if (isFall()) {
            System.out.println("栈满！");
            return;
        }
        stack[++top] = value;
    }
    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //循环遍历栈
    public void showStack() {
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
    //查看栈顶数据
    public int peek(){
        return stack[top];
    }
    //判断优先级
    public int priority(int val) {
        if (val == '*' || val == '/') {
            return 1;
        }else if (val == '-' || val == '+') {
            return 0;
        }else {
            return -1;
        }
    }
    //判断是否是操作字符
    public boolean isOper(char oper) {
        if (oper == '+' || oper == '-' || oper == '*' || oper == '/'){
            return true;
        }
        return false;
    }
    //计算结果
    public int cla(int num1, int num2, char oper) {
        int res = 0;
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;//注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
            default:
                throw new RuntimeException("计算错误！");
        }
        return res;
    }
}