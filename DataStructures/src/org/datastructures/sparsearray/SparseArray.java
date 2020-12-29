package org.datastructures.sparsearray;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/20 - 18:04
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始二维数组
        //0:表示没有数据，1：表示黑子，2表示篮子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;//第二行，第三列一个黑子
        chessArr1[2][3] = 2;//第三行，第四列一个黑子
        chessArr1[2][4] = 1;//第三行，第五列一个黑子
        System.out.println("行数：" + chessArr1.length);//行数
        //输出二维数组
        for (int[] row : chessArr1) {
            for (int i = 0; i < row.length; i++) {
                System.out.printf("%d\t" , row[i]);
            }
            System.out.println();
        }
        //遍历数组中的有效元素
        int count = 0;//有效元素
        for (int[] row : chessArr1) {
            for (int i : row) {
                if (i != 0) {
                    count ++;
                }
            }
        }
        System.out.println("有效元素个数："+count);

        //创建稀疏数组
        //第一行表示原先的数组几行几列
        //第一个【行数】第二个【列数】
        int sparseArray[][] = new int[count+1][3];
        //初始化稀疏数组第一行的信息
        sparseArray[0][0] = 11;//第一行第一列
        sparseArray[0][1] = 11;//第一行第二列
        sparseArray[0][2] = count;//第一行第三列一共多少有效值
        //转换为稀疏数组
        int row = 0;//行数
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    row++;
                    sparseArray[row][0] = i;
                    sparseArray[row][1] = j;
                    sparseArray[row][2] = chessArr1[i][j];
                }
            }
        }
        /*
        * io将稀疏数组转换为文件
        * */
        outputStreamObject(sparseArray,"sparse.data");
        /*
        * 从io流文件中获取对象
        * */
        int[][] recover = (int[][]) inputStreamObject("sparse.data");
        sparseArray = recover;//将恢复的数组重新赋值

        //输出稀疏数组
        for (int i = 0; i < count+1; i++) {
            System.out.printf("%d\t%d\t%d\t\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        //将稀疏数组转换(恢复)为普通二维数组
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];//初始化数组
        //遍历稀疏数组
        for (int i = 1; i < count+1; i++) {
            //依次取出
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        //重新遍历数组
        //恢复后的数组
        System.out.println("恢复后的：");
        for (int[] rows : chessArr2) {
            for (int li : rows) {
                System.out.printf("%d\t" , li);
            }
            System.out.println();
        }
        
    }

    public static void outputStreamObject(Object obj,String path) {
        ObjectOutputStream oops = null;
        try {
            oops = new ObjectOutputStream(new FileOutputStream(path));
            oops.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oops != null) {
                try {
                    oops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static Object inputStreamObject(String path) {
        ObjectInputStream ois = null;
        Object o = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            o = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return o;
    }
}
