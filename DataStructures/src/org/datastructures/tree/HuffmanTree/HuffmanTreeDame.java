package org.datastructures.tree.HuffmanTree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 赫夫曼树/霍夫曼树/哈夫曼树
 * @author lijichen
 * @date 2020/9/2 - 16:19
 */
public class HuffmanTreeDame {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    //递归前序遍历
    public static void preOrder(Node root) {
        if (root == null) {
            return;
        }
        root.preOrder();
    }

    //创建赫夫曼树
    public static Node createHuffmanTree(int[] arr) {
        //将arr逐个置成节点
        List<Node> list = new ArrayList();
        for (int item : arr) {
            list.add(new Node(item));//将数组中的各个值放入集合中
        }

        //循环调整排序数组，直到集合中只剩下了一个
        while (list.size() > 1) {
            Collections.sort(list);//将list中的数据进行排序
            //取出集合中的最小值（节点），和次小节点
            Node leftNode = list.get(0);
            Node rightNode = list.get(1);
            //构建成一颗新的二叉树，新的二叉树的权为leftNode和rightNode两者的和
            Node node = new Node(leftNode.value + rightNode.value);
            //将node这个节点的左节点和右节点进行赋值
            node.left = leftNode;
            node.right = rightNode;
            //将新的二叉树放入集合
            list.add(node);
            //删除掉leftNode 和 rightNode节点
            list.remove(leftNode);
            list.remove(rightNode);
        }
        return list.get(0);//返回最后的二叉树，即为赫夫曼二叉树
    }
}

//节点
//实现comparable接口，实现可排序
class Node implements Comparable<Node> {
    public int value;
    public Node left;
    public Node right;

    //递归前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value;//降序排序
    }
}
