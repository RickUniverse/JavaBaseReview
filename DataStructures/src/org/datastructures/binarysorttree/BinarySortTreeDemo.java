package org.datastructures.binarysorttree;

/**
 * 二叉排序树
 * 妙
 * @author lijichen
 * @date 2020/9/3 - 18:09
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2,4};
        //创建二叉排序树
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int item : arr) {
            binarySortTree.add(new Node(item));
        }

        //测试删除叶子节点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(9);
//        binarySortTree.delNode(12);

        //测试删除只有一个叶子节点
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(5);

        //测试删除有二个叶子节点
//        binarySortTree.delNode(7);

        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(12);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(2);
        binarySortTree.delNode(4);
//        System.out.println(binarySortTree.getRoot().left);
        binarySortTree.delNode(1);
        binarySortTree.delNode(10);
        //中序遍历
        binarySortTree.infixOrder();
    }
}

//二叉排序树
class BinarySortTree {
    //头节点
    private Node root;

    public Node getRoot() {
        return root;
    }

    //添加节点
    public void add(Node node) {
        if (node != null) {
            if (root != null) {
                root.add(node);
            } else {
                root = node;//如果头节点为空，直接给头节点添加
            }
        }
    }
    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("链表为空");
            return;
        }
        root.infixOrder();
    }

    //查找需要删除的方法的节点
    public Node search(int value) {
        if (root == null) {
            return null;
        } else {
            return root.search(value);
        }
    }
    //查找需要删除的节点的父节点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }

    /**
     * 右子树找最小,右子树指的就是传过来的node
     * 1,返回以node为根节点的二叉排序树的最小节点的值
     * 2，删除node为根节点的二叉排序树的最小结点
     * @param node 传入的节点，当做二叉排序树的根节点
     * @return 返回以node节点为跟节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node) {
        Node targetNode = node;
        //循环的查找左子节点就能找到最小值
        while (targetNode.left != null) {
            targetNode = targetNode.left;
        }
        //这时，targetNode就指向的最小节点
        //删除最小节点
        delNode(targetNode.value);
        return targetNode.value;//返回最小值
    }

    /**
     * 左子树找最大的,左子树指的就是传过来的node
     * @param node 传入的节点，当做二叉排序树的根节点
     * @return 返回以node节点为跟节点的二叉排序树的最大结点的值
     */
    public int delLeftTreeMax(Node node) {
        Node targetNode = node;
        while (targetNode.right != null) {
            targetNode = targetNode.right;
        }
        //此时已经找到的最大的
        //删除最大的
        delNode(targetNode.value);
        return targetNode.value;
    }

    /**
     * 删除节点
     * @param value 删除节点的值
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            //获取要删除的节点
            Node targetNode = search(value);
            if (targetNode == null) {
                //没有找到要删除的节点
                return;
            }
            //如果当前root节点只有一个节点，即本山，则获取的要删除的节点就是当前节点
            if (root.left == null && root.right == null) {
                root = null;//置空root
                return;
            }
            //获取要删除节点的父节点
            Node parent = searchParent(value);
            //删除叶子节点的情况
            if (targetNode.left == null && targetNode.right == null) {
                //如果要删除节点是父节点的左子节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;//置空左节点
                } else if (parent.right != null && parent.right.value == value) {
                    //如果要删除节点是父节点的左子节点
                    parent.right = null;//置空右节点
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                //右子树找最小
                //需要删除的节点有两个子节点的时候
//                int minVal = delRightTreeMin(targetNode.right);//从右子节点开始找最小的
//                targetNode.value = minVal;
                //左子树找最大的
                int maxVal = delLeftTreeMax(targetNode.left);
                targetNode.value = maxVal;
            } else {
                //剩下的是只有一个字节点的情况
                //如果是targetNode的left子节点不为空
                if (targetNode.left != null) {
                    //如果父节点的left子节点为空，说明只能是right子节点
                    if (parent != null) {//如果父节点不为空
                        /*
                         * parent.left != null : 需要有不然有可能会出bug
                         * */
                        if (parent.left != null && parent.left.value == value) {
                            //如果targetNode是parent的left子节点
                            parent.left = targetNode.left;
                        } else {
                            //如果targetNode是parent的right子节点
                            parent.right = targetNode.left;
                        }
                    } else {
                        //如果父节点为空，且targetNode只有一个left节点的话
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {//如果父节点不为空
                        //如果是targetNode的right子节点不为空
                        if (parent.left != null && parent.left.value == value) {
                            //如果targetNode是parent的left子节点
                            parent.left = targetNode.right;
                        } else {
                            //如果targetNode是parent的right子节点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }
    }
}

//创建节点
class Node {
    int value;//值
    Node left;//存储比value小的节点引用
    Node right;//存储比value大的节点引用

    /**
     * 查找要删除的节点
     * @param value 要删除节点的值
     * @return 返回要删除的节点
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;//找到了
        } else if (value < this.value) {//如果要查找的值小于当前节点的值，则向左查找
            if (this.left == null) {
                return null;//没有找到
            }
            return this.left.search(value);//向左递归查找
        } else {//如果要查找的值大于等于当前节点的值，则向右查找
            if (this.right == null) {
                return null;//没有找到
            }
            return this.right.search(value);
        }
    }

    /**
     * 获取要删除节点的父节点
     * @param value 要删除节点的值
     * @return 返回要删除节点的父节点，没有的话为null
     */
    public Node searchParent(int value) {
        //要删除节点的父节点的left或right的value值判断
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;//返回当前节点，当前节点为要删除节点的父节点
        } else {
            //如果没有则递归查找
            if (value < this.value && this.left != null) {//想左子树递归
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {//向右子树递归
                return this.right.searchParent(value);
            } else {
                return null;//没有找到父节点
            }
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public Node(int value) {
        this.value = value;
    }

    //添加节点
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //如果添加的节点的value小于当前节点的值
        if (node.value < this.value) {
            //将该节点递归添加到节点的左子节点中
            if (this.left == null) {
                this.left = node;
            } else {
                //向左递归添加
                this.left.add(node);
            }
        } else {//如果添加的节点的value大于或者等于当前节点的值
            if (this.right == null) {
                this.right = node;
            } else {
                //向右递归添加
                this.right.add(node);
            }
        }
    }

    //循环中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();//向左递归
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();//向右递归
        }
    }
}
