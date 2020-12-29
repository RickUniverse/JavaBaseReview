package org.datastructures.recursion;

/**
 * 迷宫回溯问题
 * @author lijichen
 * @date 2020/8/26 - 16:24
 */
public class MiGong {
    public static void main(String[] args) {
        //设置一个二维数组
        int[][] map = new int[8][7];
        //输出二维数组
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        //设置上下为墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //设置左右为墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //开始遍历找路
        setWay(map,1,1);

        //输出二维数组
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //策略方法
    public static boolean setWay(int[][] map, int i, int j) {
        //如果找到了map[6][5],说明找到终点了
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//说明没有走过
                //假定该点可以走通
                map[i][j] = 2;
                //按照下--右--上--左的顺序找
                if (setWay(map,i + 1,j)) {
                    return true;
                } else if (setWay(map,i,j + 1)) {
                    return true;
                } else if (setWay(map,i - 1,j)) {
                    return true;
                } else if (setWay(map,i,j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;//都走不通说明此路不通
                    return false;
                }
            } else {//如果map[i][j] ！= 0可能就是 1,2,3
                return false;
            }
        }
    }
    //策略方法
    public static boolean setWay2(int[][] map, int i, int j) {
        //如果找到了map[6][5],说明找到终点了
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//说明没有走过
                //假定该点可以走通
                map[i][j] = 2;
                //按照下--右--上--左的顺序找
                if (setWay(map,i + 1,j)) {
                    return true;
                } else if (setWay(map,i,j + 1)) {
                    return true;
                } else if (setWay(map,i - 1,j)) {
                    return true;
                } else if (setWay(map,i,j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;//都走不通说明此路不通
                    return false;
                }
            } else {//如果map[i][j] ！= 0可能就是 1,2,3
                return false;
            }
        }
    }
}
