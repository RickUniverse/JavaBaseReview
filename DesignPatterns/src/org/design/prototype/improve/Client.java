package org.design.prototype.improve;

/**
 * @author lijichen
 * @date 2020/8/13 - 16:11
 */
public class Client {
    public static void main(String[] args) {
        PrototypeSheep ps = new PrototypeSheep(123,"er");
        PrototypeSheep clone1 = (PrototypeSheep)ps.clone();
        PrototypeSheep clone2 = (PrototypeSheep)ps.clone();
        PrototypeSheep clone3 = (PrototypeSheep)ps.clone();
        PrototypeSheep clone4 = (PrototypeSheep)ps.clone();
        PrototypeSheep clone5 = (PrototypeSheep)ps.clone();

        System.out.println(clone1);
        System.out.println(clone2);
        System.out.println(clone3);
        System.out.println(clone4);
        System.out.println(clone5);
    }
}
