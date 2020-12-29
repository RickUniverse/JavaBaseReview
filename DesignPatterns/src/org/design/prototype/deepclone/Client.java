package org.design.prototype.deepclone;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/13 - 17:04
 */
//深克隆
public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepCloneableTarget dct = new DeepCloneableTarget("qqq",new DeepProtoType("eee",123));

        //使用方式一：clone克隆
        //DeepCloneableTarget dct2 = (DeepCloneableTarget) dct.clone();
        //使用方式二：序列化
        DeepCloneableTarget dct2 = dct.DeepClone();
        System.out.println("wile:" + dct.getDeepProtoType().hashCode() + "cloned:" + dct2.getDeepProtoType().hashCode());
    }
}
class DeepProtoType implements Cloneable , Serializable {
    private String name;
    private int age;

    public DeepProtoType(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
class DeepCloneableTarget implements Cloneable , Serializable {
    private String name;
    private DeepProtoType deepProtoType;

    public DeepProtoType getDeepProtoType() {
        return deepProtoType;
    }

    public DeepCloneableTarget(String name, DeepProtoType deepProtoType) {
        this.name = name;
        this.deepProtoType = deepProtoType;
    }

    //深拷贝方式1：clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        DeepCloneableTarget dct = (DeepCloneableTarget) super.clone();
        dct.deepProtoType = (DeepProtoType)dct.deepProtoType.clone();//将属性克隆赋值
        return dct;
    }
    //深拷贝方式2：推荐
    public DeepCloneableTarget DeepClone() {

        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        DeepCloneableTarget dct = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            dct = (DeepCloneableTarget) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bais != null){
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dct;
    }
}