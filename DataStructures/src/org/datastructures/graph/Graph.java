package org.datastructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * 图
 * @author lijichen
 * @date 2020/9/5 - 15:04
 */
public class Graph {
    //存储顶点的集合
    private ArrayList<String> vertexList;
    //存储图对应的邻接矩阵
    private int[][] edges;
    //表示边的数目
    private int numOfEdges;
    //定义boolean数组标记某个结点是否被访问
    private boolean[] isVisited;

    public static void main(String[] args) {
        //结点个数
        int n = 5;
        Graph graph = new Graph(n);
        //创建顶点数组
        String[] vertex = {"A","B","C","D","E"};
        for (String s : vertex) {
            graph.insertVertex(s);
        }
        //添加边 A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        //显示矩阵
        graph.show();

        //深度优先遍历
        graph.dfs();
        System.out.println();
        //广度优先遍历
        graph.bfs();
    }

    public Graph(int n) {
        //初始化顶点集合
        this.vertexList = new ArrayList<>();
        //初始化矩阵
        this.edges = new int[n][n];
        //边的数目
        this.numOfEdges = 0;
    }

    /**
     * 得到第一个领结节点的下标w
     * @param index
     * @return 如果存在就返回对应的下标，否则就返回-1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻结结点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][v2] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     * @param isVisited
     * @param i
     */
    public void dfs(boolean[] isVisited, int i) {
        //首先我们访问该结点，输出
        System.out.print(getValueByIndex(i) + "=>");
        //将结点设置为已访问
        isVisited[i] = true;
        //查找结点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有
            if (!isVisited[w]) {
                dfs(isVisited,w);
            }
            //如果节点已经被访问过
            w = getNextNeighbor(i,w);
        }
    }
    //对dfs进行重载，遍历我们所有的结点，并进行dfs
    public void dfs() {
        //初始化boolean
        this.isVisited = new boolean[vertexList.size()];
        //遍历所有节点，进行dfs【回溯】
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited,i);
            }
        }
    }

    //对一个节点进行广度优先遍历的方法
    private void bfs(boolean[] isVisited, int i) {
        int u; //表示队列的头节点对应下标
        int w;//邻接节点w
        //队列，记录节点访问的顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        while (!queue.isEmpty()) {
            //取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            //得到第一个邻接节点的下标w
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                //是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    //标记已访问u
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱点，找w后面的下一个邻接点
                w = getNextNeighbor(u,w);//体现出我们的广度优先
            }
        }
    }

    //遍历所有的结点，都进行广度优先搜索
    public void bfs() {
        //初始化boolean
        this.isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }


    //显示图的矩阵
    public void show() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //返回顶点数量
    public int getNumOfVertex() {
        return vertexList.size();
    }
    //返回边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回结点(顶点）对应的下标
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //添加顶点
    public void insertVertex(String str) {
        vertexList.add(str);
    }
    /**
     * 添加边
     * @param v1 下标
     * @param v2 下标
     * @param weight 0 or 1
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;//因为是无向图
        numOfEdges++;
    }
}
