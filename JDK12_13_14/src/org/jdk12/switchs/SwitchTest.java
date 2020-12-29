package org.jdk12.switchs;

import javax.imageio.metadata.IIOInvalidTreeException;

/**
 * @author lijichen
 * @date 2020/8/9 - 19:09
 */
public class SwitchTest {
    public static void main(String[] args) {
        Furit f = Furit.CCC;
        int i = switch (f){
            case AAA -> 1;
            case BBB -> 2;
            case CCC,DDD,EEE -> 345;
            default -> throw new IllegalStateException("异常:" + f);
        };
    }
}
enum Furit{
    AAA,BBB,CCC,DDD,EEE;
}