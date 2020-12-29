package org.datastructures.tree;

/**
 * 顺序存储二叉树
 * @author lijichen
 * @date 2020/9/1 - 14:34
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        //创建数组
        int[] arr = {1,2,3,4,5,6,7};
        //创建树
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
//        arrBinaryTree.preOrder();
//        arrBinaryTree.infixOrder();
        arrBinaryTree.postOrder();
    }
}

//创建树
class ArrBinaryTree {
    //创建数组
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    //重载前序遍历
    public void preOrder() {
        this.preOrder(0);
    }
    //重载中序遍历
    public void infixOrder() {
        this.infixOrder(0);
    }
    //重载后序遍历
    public void postOrder() {
        this.postOrder(0);
    }

    //前序遍历
    public void preOrder(int index) {
        //判断数组是否有数据
        if (arr == null || arr.length == 0) {
            return;
        }
        //先输出当前节点
        System.out.println(arr[index]);
        //如果计算的左节点大于的数组长度，就不能递归
        if ((2 * index + 1) < arr.length) {
            //左递归
            preOrder((2 * index + 1));
        }
        //(2 * index + 2)：求出右节点
        if ((2 * index + 2) < arr.length) {
            //右递归
            preOrder((2 * index + 2));
        }
    }
    //中序遍历
    public void infixOrder(int index) {
        //判断数组是否有数据
        if (arr == null || arr.length == 0) {
            return;
        }
        //如果计算的左节点大于的数组长度，就不能递归
        if ((2 * index + 1) < arr.length) {
            //左递归
            preOrder((2 * index + 1));
        }
        //输出当前节点
        System.out.println(arr[index]);
        //(2 * index + 2)：求出右节点
        if ((2 * index + 2) < arr.length) {
            //右递归
            preOrder((2 * index + 2));
        }
    }
    //后序遍历
    public void postOrder(int index) {
        //判断数组是否有数据
        if (arr == null || arr.length == 0) {
            return;
        }
        //如果计算的左节点大于的数组长度，就不能递归
        if ((2 * index + 1) < arr.length) {
            //左递归
            preOrder((2 * index + 1));
        }
        //(2 * index + 2)：求出右节点
        if ((2 * index + 2) < arr.length) {
            //右递归
            preOrder((2 * index + 2));
        }
        //输出当前节点
        System.out.println(arr[index]);
    }
}
