package org.datastructures.tree.threadedbinarytree;

/**
 * 线索化二叉树
 * @author lijichen
 * @date 2020/9/1 - 15:58
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试线索化
        //创建节点
        HeroNode root = new HeroNode(1,"q");
        HeroNode heroNode2 = new HeroNode(3,"q");
        HeroNode heroNode3 = new HeroNode(6,"q");
        HeroNode heroNode4 = new HeroNode(8,"q");
        HeroNode heroNode5 = new HeroNode(10,"q");
        HeroNode heroNode6 = new HeroNode(14,"q");
        root.setLeft(heroNode2);
        root.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        //创建线索化二叉树
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //sout
        System.out.println(heroNode5.getLeft());
        System.out.println(heroNode5.getRight());
        System.out.println("_________________");
        //线索化输出
        threadedBinaryTree.threadedList();
    }
}

//线索化二叉树
class ThreadedBinaryTree {
    //根节点
    private HeroNode root;

    //需要一个属性，维护前驱节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载线索化方法
    public void threadedNodes() {
        //首先判断root节点是否为空
        if (root == null) {
            return;
        }
        this.threadedNodes(root);
    }

    //遍历线索化二叉树
    public void threadedList() {
        //维护一个指针
        HeroNode node = root;
        while (node != null) {
            //找到leftType为1的节点
            while (node.getLeftType() == 0) {
                //不是则指针后移
                node = node.getLeft();
            }
            //上面的循环退出后说明找到了
            System.out.println(node);
            //如果当前right节点是指向的后继节点，就一直输出
            while (node.getRightType() == 1) {
                //获取后继节点
                node = node.getRight();
                //打印后继节点
                System.out.println(node);
            }
            //最后获取节点的right节点，完成遍历
            node = node.getRight();
        }
    }

    /**
     * 中序线索化二叉树
     * @param heroNode 需要线索化的变量
     */
    public void threadedNodes(HeroNode heroNode) {
        //如果找到了为空的heroNode就返回
        if (heroNode == null) {
            return;
        }
         //1，向左线索化子树
        threadedNodes(heroNode.getLeft());
        //2.线索化操作
        //处理heroNode的left节点，第一次this.pre = null
        //第一次，所以前驱节点为空
        if (heroNode.getLeft() == null) {
            //如果是第一个前驱节点，则不会给left值，因为第一册pre为null
            heroNode.setLeft(pre);
            //证明heroNode的left指向的是前驱节点，即使没有前驱节点
            heroNode.setLeftType(1);//1：即为指向前驱节点
        }
        //处理后继节点
        //下面的判断语句是为了
        //递归回到该节点的上一个节点后，判断作为前驱节点的pre.right是否为空
        //如果为空，则pre，即代码运行到这的当前节点的前驱节点的后继节点为当前节点
        //因为pre初始化的值时null，所以需要判断是否为空，等到下一次递归pre不为空时在进行处理
        if (pre != null && pre.getRight() == null) {
            //修改后继节点为当前代码运行时的节点
            pre.setRight(heroNode);
            //说明是指向的后继节点
            pre.setRightType(1);
        }
        //!!!!,将当前节点设置为前驱节点
        pre = heroNode;
        //3，向右线索化子树
        threadedNodes(heroNode.getRight());
    }


    //删除节点
    public void deleteNode(int no) {
        //先判断roo节点是否为空
        if (root != null) {
            //不是空则判断该节点是否为需要删除的节点
            if (root.getNo() == no) {
                //再次判断roo节点的left和right是否存在
                if (root.getLeft() != null) {
                    //如果右边也有节点，就先将root的right置为该节点
                    HeroNode temp = null;//保存节点
                    if (root.getRight() != null) {
                        temp = root.getRight();
                    }
                    //之后，则将left提到root节点
                    root = root.getLeft();
                    //最后，将右边（right）的字节点，给root的right
                    root.setRight(temp);
                    return;
                } else if (root.getRight() != null) {
                    //如果右边不为空
                    root = root.getRight();//就将右边的提到root
                    return;
                } else {
                    //直接置空
                    root = null;
                    return;
                }
            } else {
                //调用递归方法
                root.deleteNode(no);
            }
        }
    }


    //前序遍历
    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树空");
        }
    }
    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树空");
        }
    }
    //后序遍历
    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树空");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//heronode
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;//右边的树
    private HeroNode right;//左边的树

    //left和right树的类型
    //leftType = 0;表示指向的左子树
    //leftType = 1;表示指向的是前驱节点
    //rightType = 0;表示指向的右子树
    //rightType = 1;表示指向的是后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //删除节点(该方法没毛病)
    public void deleteNode(int no) {
        //判断当前节点的左节点是否为空，且是否为需要删除的节点
        if (this.left != null && this.left.no == no) {
            //如果是则判断该节点是否有左节点或者右节点
            if (this.left.left != null) {
                //还需要判断有没有右节点
                HeroNode temp = null;
                if (this.left.right != null) {
                    //有则
                    temp = this.left.right;
                }
                //存在左节点，就将左节点提到删除后的节点的位置
                this.left = this.left.left;//提上去
                //提上去之后
                // 需要判断提之后的节点是否有right节点，如果有则找到最后一个right，在将temp赋给最后一个为空的right
                HeroNode getRight = this.left;//维护一个指针
                while (getRight.right != null){
                    //指针后移
                    getRight = getRight.right;
                }
                //while循环完毕之后就已经找到了
                //则
                getRight.right = temp;//将temp给到最后的right
                return;
            } else if (this.left.right != null) {
                //如果左节点没有，但是有右节点，则
                this.left = this.left.right;
                return;
            } else {
                //如果都没有，说明是叶子节点，直接删除掉节点
                this.left = null;
                return;
            }
        }
        //判断当前节点的右节点是否为空，且是否为需要删除的节点
        if (this.right != null && this.right.no == no) {
            //如果是则判断该节点是否有左节点或者右节点
            if (this.right.left != null) {
                //还需要判断是否存在右节点
                HeroNode temp = null;
                if (this.right.right != null) {
                    //有则
                    temp = this.right.right;
                }
                //存在左节点，就将左节点提到删除后的节点的位置
                this.right = this.right.left;//提上去

                // 需要判断提之后的节点是否有right节点，如果有则找到最后一个right，在将temp赋给最后一个为空的right
                HeroNode getRight = this.right;//维护一个指针
                while (getRight.right != null){
                    //指针后移
                    getRight = getRight.right;
                }
                //while循环完毕之后就已经找到了
                //则
                getRight.right = temp;//将temp给到最后的right
                return;
            } else if (this.right.right != null) {
                //如果左节点没有，但是有右节点，则
                this.right = this.right.right;
                return;
            } else {
                //如果都没有，说明是叶子节点，直接删除掉节点
                this.left = null;
                return;
            }
        }

        //向左递归删除
        if (this.left != null) {
            this.left.deleteNode(no);
        }
        //向右递归删除
        if (this.right != null) {
            this.right.deleteNode(no);
        }
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }
    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        //先比较当前节点
        if (this.no == no) {
            return this;
        }
        //保存找到的节点的变量
        HeroNode resNode = null;
        //向左子树节点查找
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        //判断是否查找到
        if (resNode != null) {
            return resNode;
        }
        //否则向右子树查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        //直接返回
        return resNode;
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //保存找到的节点的变量
        HeroNode resNode = null;
        //向左子树查找
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        //判断是否找到
        if (resNode != null) {
            return resNode;
        }
        //否则跟当前节点进行比较
        if (this.no == no) {
            return this;
        }
        //向右子树查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        //不管有没有找到都返回
        return resNode;
    }
    //后序查找
    public HeroNode postOrderSearch(int no){
        //保存
        HeroNode resNode = null;
        //向左子树
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        //判断向左子树是否找到
        if (resNode != null) {
            return resNode;
        }
        //没有找到则向右子树
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        //判断向右是否找到
        if (resNode != null) {
            return resNode;
        }
        //否则跟当前节点比较
        if (this.no == no) {
            return this;
        }
        return resNode;
    }
}