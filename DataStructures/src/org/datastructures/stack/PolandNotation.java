package org.datastructures.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式（逆波兰表达式）
 * @author lijichen
 * @date 2020/8/23 - 18:56
 */
public class PolandNotation {
    public static void main(String[] args) {
        //中缀表达式转后缀表达式
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpression = toInfixExpressionList(expression);//获取中缀表达式集合
        System.out.println("中缀表达式"+infixExpression);
        List<String> suffixExpression = praseSuffixExpressionList(infixExpression);//转换为后缀表达式
        System.out.println("后缀表达式"+suffixExpression);
        //运算后缀表达式
        int result = calculate(suffixExpression);
        System.out.println("结果为："+result);


        /*//先定义逆波兰表达式:原先的（3+4）*5-6
        String suffixExpression = "3 4 + 5 * 6 -";

        List<String> listString = getListString(suffixExpression);

        //运算结构
        int calculate = calculate(listString);
        System.out.println(calculate);*/
    }

    /*
    * 中缀表达式转换
    * 将得到的中缀表达式转换为后缀表达式
    * */
    public static List<String> praseSuffixExpressionList(List<String> ls) {
        //定义s1栈
        Stack<String> s1 = new Stack<String>();
        //使用ArrayList替换Stack栈
        List<String> s2 = new ArrayList<String>();
        //遍历中缀表达式list
        for (String item : ls) {
            //如果是一个数加入s2集合
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {//如果是(符号，就压入栈
                s1.push(item);
            } else if (item.equals(")")) {
                /*
                * 如果是)符号，依次s1棧頂的运算符，并压入s2，直道遇到）左括号为止，此时将舍弃这一对括号
                * */
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//此时s1的栈顶是 ( 符号，这个操作就是舍弃它
            } else {
                //当item的优先级小于等于s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到与s1新的栈顶运算符作比较
                while (s1.size() != 0 && Operation.getPriority(s1.peek()) >= Operation.getPriority(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }
        //将s1中剩余的运算符依次出栈并加入s2集合中
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//因为是使用集合，不需要翻转
    }

    //将中缀表达式转换为对应的list
    public static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int i = 0;//这是指针，用来遍历expression参数
        String str;//对多位数的拼接
        char c;//每遍历到一个字符就放入到c
        do {
            //如果是非数字
            if ((c = expression.charAt(i)) <= 48 || (c = expression.charAt(i)) > 57 ) {
                list.add("" + c);
                i++;//指针后移
            } else {//如果是数字
                str = "";//先将str置空,每次找到数字都会置空
                while (i < expression.length() && (c = expression.charAt(i)) >= 48 && (c = expression.charAt(i)) <= 57) {
                    str += c;//拼接多位数
                    i++;//指针后移
                }
                list.add(str);//添加
            }

        } while (i < expression.length());

        return list;
    }


    //放入逆波兰表达式返回一个list数组
    public static List<String> getListString(String suffixExpression) {
        List<String> list = new ArrayList<>();
        String[] s = suffixExpression.split(" ");
        for (String s1 : s) {
            list.add(s1);
        }
        return list;
    }
    //完成对逆波兰表达式的运算
    public static int calculate(List<String> list) {
        //创建栈
        Stack<String> stack = new Stack<>();
        //遍历集合
        for (String item : list) {
            //使用正则表达式取出数字
            if (item.matches("\\d+")){
                //数字直接入栈
                stack.push(item);
            } else {
                //出栈两个数，进行运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                }
                //运算结果入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }
}
//返回算符优先级的类
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回优先级的方法
    public static int getPriority(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
//                System.out.println(operation);
//                throw new RuntimeException("符号不正确！");
                //不能抛异常
                result = 0;
                break;
        }
        return result;
    }
}