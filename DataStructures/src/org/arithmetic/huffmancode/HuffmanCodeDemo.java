package org.arithmetic.huffmancode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码
 * @author lijichen
 * @date 2020/9/2 - 18:06
 */
public class HuffmanCodeDemo {

    //1，创建map，保存赫夫曼编码
    static Map<Byte,String> huffmanCode = new HashMap();
    //2，需要去拼接赫夫曼编码的路径,创建stringbuilder
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 重载getCode方法，简化操作
     * @param root 传入的root节点
     * @return 赫夫曼编码
     */
    private static Map<Byte,String> getCode(CharNode root) {
        if (root == null) {
            return null;
        }
        //向左递归。说明：此时root节点的data一定为空，证明是非叶子节点
        getCode(root.left,"0",stringBuilder);
        //向右递归
        getCode(root.right,"1",stringBuilder);
        return huffmanCode;
    }

    /**
     * 将传入的赫夫曼树的赫夫曼编码的到，并放入hoffmancode的map集合中
     * @param node 头节点，root
     * @param code 路径 ： 0 表示 左子树，1表示右子树
     * @param stringBuilder 用于拼接路径
     */
    private static void getCode(CharNode node, String code, StringBuilder stringBuilder) {
        //首先创建一个stringbuilder
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        //将路径进行拼接
        stringBuilder1.append(code);
        if (node != null) {//如果node==null不处理
            //递归
            if (node.data == null) {//说明是非叶子节点
                //向左递归
                getCode(node.left,"0",stringBuilder1);//左子树为：0
                //向右递归
                getCode(node.right,"1",stringBuilder1);//右子树为：1
            } else {
                //如果找到了叶子节点
                huffmanCode.put(node.data,stringBuilder1.toString());//将字符对应编码放入huffmancode
            }
        }
    }

    /**
     * 压缩
     * @param bytes 这时原始字符串对应的byte
     * @param huffmanCodeMap 生成的霍夫曼编码map
     * @return 压缩后的byte[]
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodeMap) {
        //生成霍夫曼编码长字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodeMap.get(b));//拼接各个字符对应的编码
        }

        //数组的长度
        //可以使用一句话：int len = (stringBuilder.length() + 7 ) / 8  代替;
        int len = 0;
        if (stringBuilder.length() % 8 == 0) {//够八位
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        //最终byte
        byte[] huffmanCode = new byte[len];
        //循环反码
        int index = 0;//计时器
        for (int i = 0; i < stringBuilder.length(); i += 8) {//没八位一次
            String sub = null;
            if (i+8 > stringBuilder.length()) {//不够八位
                sub = stringBuilder.substring(i);//直接到最后
            } else {
                sub = stringBuilder.substring(i,i + 8);//往后找8个字符
            }
            huffmanCode[index] = (byte)Integer.parseInt(sub,2);//转为2位
            index++;
        }
        return huffmanCode;
    }

    /**
     * 将一个字符数组以霍夫曼编码进行压缩
     * @param bytes 原本的字符（asoll）byte数组
     * @return 返回压缩后的byte数组
     */
    public static byte[] huffmanZip(byte[] bytes) {
        //装换为字符集合
        List<CharNode> charNodeList = parseCharNodeList(bytes);
        //得到创建的赫夫曼树
        CharNode huffmanTree = createHuffmanTree(charNodeList);
        //进行赫夫曼编码
        Map<Byte, String> huffmanCodeMap = getCode(huffmanTree);
        //压缩霍夫曼编码
        byte[] huffmanZip = zip(bytes, huffmanCodeMap);
        return huffmanZip;
    }

    /**
     * 将一个byte转成一个二进制的字符串
     * @param flag 标识是否需要补高位，true为需要，false为不需要
     * @param b 传入的byte
     * @return 是该b对应的二进制字符串
     */
    public static String byteToBitString(boolean flag, byte b) {
        //先转换为int
        int temp = b;
        //如果是正数就补高位，如果是最后一个字节无需补高位
        if (flag) {
            temp |= 256;
        }
        String binaryString = Integer.toBinaryString(temp);
        if (flag) {
            return binaryString.substring(binaryString.length() - 8);
        } else {
            return binaryString;
        }
    }

    /**
     * 将压缩后的bytes转换为压缩前的bytes
     * @param huffmanBytes 压缩后的
     * @param huffmanCodeMap 编码表
     * @return
     */
    public static byte[] decode(byte[] huffmanBytes, Map<Byte,String> huffmanCodeMap) {

        //先得到赫夫曼编码表对应的编码字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,b));
        }

        //将赫夫曼编码表中的键值对翻过来
        Map<String,Byte> huffmanCodes = new HashMap<>();
        for (Map.Entry<Byte,String> item : huffmanCodeMap.entrySet()) {
            huffmanCodes.put(item.getValue(),item.getKey());
        }

        //循环编码字符串，返回bytes,放入list集合
        List<Byte> byteList = new ArrayList<Byte>();
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                b = huffmanCodes.get(stringBuilder.substring(i,i + count));
                if (b == null) {
                    count++;//说明没有匹配到，再次进行匹配
                } else {
                    //匹配到
                    flag = false;
                }
            }
            //匹配到之后添加到集合中
            byteList.add(b);
            i += count;//从count开始查找
        }

        //将集合转换为byte数组
        byte[] bytes = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            bytes[i] = byteList.get(i);
        }
        return bytes;
    }

    /**
     * 使用赫夫曼编码压缩文件
     * @param srcFile 源文件路径
     * @param dstFile 压缩后文件路径
     */
    public static void fileZip(String srcFile, String dstFile) {
        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //创建输入流
            is = new FileInputStream(srcFile);
            //输出流
            //文件输出流
            os = new FileOutputStream(dstFile);
            //对象输出流
            oos = new ObjectOutputStream(os);

            //创建跟文件相同大小的数组
            byte[] original = new byte[is.available()];
            is.read(original);//向byte中写入数据

            //压缩，赫夫曼压缩后的数组
            byte[] zip = huffmanZip(original);
            //写入文件
            oos.writeObject(zip);//将压缩后的文件写入
            //！！！将霍夫曼编码写入
            oos.writeObject(huffmanCode);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解压缩文件
     * @param zipFile 压缩文件路径
     * @param dstFile 解压到那个地方
     */
    public static void fileUnZip(String zipFile, String dstFile) {
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            //创建输入流
            //文件输入流
            is = new FileInputStream(zipFile);
            //对象输入流
            ois = new ObjectInputStream(is);
            //文件输出流
            os = new FileOutputStream(dstFile);

            //得到压缩后的赫夫曼数组
            byte[] huffmanByte = (byte[]) ois.readObject();
            //赫夫曼编码表
            Map<Byte,String> huffmanMap = (Map<Byte,String>) ois.readObject();
            //解压
            byte[] original = decode(huffmanByte,huffmanMap);
            //将解压后的文件输出
            os.write(original);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        //测试压缩文件
        fileZip("E:\\yangyangli\\Pictures\\Camera Roll\\1.jpg","E:\\yangyangli\\Pictures\\Camera Roll\\1.zip");

        //测试解压缩文件
        fileUnZip("E:\\yangyangli\\Pictures\\Camera Roll\\1.zip","E:\\yangyangli\\Pictures\\Camera Roll\\2.jpg");

        /*String content = "i like like like like like java do you like java";
        byte[] strContentByte = content.getBytes();

        byte[] huffmanZip = huffmanZip(strContentByte);
        //System.out.println(Arrays.toString(huffmanZip));


        byte[] bytes = decode(huffmanZip, huffmanCode);
        System.out.println(new String(bytes));*/


        /*//装换为字符集合
        List<CharNode> charNodeList = parseCharNodeList(strContentByte);
        System.out.println(charNodeList);
        //得到创建的树
        CharNode huffmanTree = createHuffmanTree(charNodeList);
        //递归前序遍历
        preOrder(huffmanTree);

        System.out.println("赫夫曼编码");
        //得到霍夫曼编码
        Map<Byte, String> huffmanCodeMap = getCode(huffmanTree);
        System.out.println(huffmanCodeMap);

        //把霍夫曼编码压缩之后
        byte[] zip = zip(strContentByte, huffmanCodeMap);
        System.out.println(Arrays.toString(zip));*/
    }

    /**
     * 递归前序遍历
     * @param root 待遍历头节点
     */
    public static void preOrder(CharNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("空");
        }
    }

    /**
     * 创建一个赫夫曼树
     * @param charNodes 字符权值等集合
     * @return 返回一个 wpl 最小的赫夫曼树
     */
    public static CharNode createHuffmanTree(List<CharNode> charNodes){
        while (charNodes.size() > 1) {
            //首先排序数组
            Collections.sort(charNodes);
            //取出最小权重和次小权重
            CharNode leftNode = charNodes.get(0);//最小
            CharNode rightNode = charNodes.get(1);//次小
            //创建新树，新树没有字符值，即data为null
            CharNode parents = new CharNode(null, leftNode.weight + rightNode.weight);
            //修改新树的节点
            parents.left = leftNode;
            parents.right = rightNode;
            //删除
            charNodes.remove(leftNode);
            charNodes.remove(rightNode);
            //將新树节点放到list中
            charNodes.add(parents);
        }
        return charNodes.get(0);//返回最终的
    }

    /**
     * 将字符串转换为list集合
     * @param arr 传入字符串拆分后的字节数组
     * @return 返回有每个字符对应的权值的集合
     */
    public static List<CharNode> parseCharNodeList(byte[] arr) {
        //创建集合
        List<CharNode> charNodeList = new ArrayList<>();
        //创建map集合，利用map的特性来进行获取各个字符出现的次数
        Map<Byte, Integer> hashMap = new HashMap<>();
        for (byte b : arr) {
            Integer count = hashMap.get(b);//map中是否有该字符
            if (count == null) {
                //如果没有就添加
                hashMap.put(b,1);//第一次添加的权值为 1
            } else {
                //如果有就让权值 + 1
                hashMap.put(b,count + 1);
            }
        }
        //循环遍历map，添加到list集合中
        for (Map.Entry<Byte,Integer> item : hashMap.entrySet()) {
            //逐一添加到list集合中
            charNodeList.add(new CharNode(item.getKey(),item.getValue()));
        }
        return charNodeList;
    }
}

//字符节点
class CharNode implements Comparable<CharNode> {
    public Byte data;//”字符“
    public int weight;//权值
    public CharNode left;//指向左节点
    public CharNode right;//指向右节点

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

    public CharNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "CharNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(CharNode o) {
        return this.weight - o.weight;
    }
}
