package org.review.enums;

/**
 * @author lijichen
 * @date 2020/7/31 - 14:35
 */
public class Season {
    //自定义枚举类
    private final String seasonName;
    private final String seasonDesc;

    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public static final Season SPRING = new Season("春天","春天来了");
    public static final Season SUMMER = new Season("XIATIAN","XIATIAN");
    public static final Season FALL = new Season("QIUTIAN","QIUTIAN");
    public static final Season WINTER = new Season("DONGTIAN","DONGTIAN");

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
interface Info{
    void show();
}
enum EnumSeason implements Info{//枚举类
    SPRING("春天","春天来了"){
        @Override
        public void show() {
            System.out.println("spring");
        }
    },
    SUMMER("XIATIAN","XIATIAN"){
        @Override
        public void show() {
            System.out.println("XIATIAN");
        }
    },
    FALL("QIUTIAN","QIUTIAN"){
        @Override
        public void show() {
            System.out.println("QIUTIAN");
        }
    },
    WINTER("DONGTIAN","DONGTIAN"){
        @Override
        public void show() {
            System.out.println("DONGTIAN");
        }
    };

    private final String seasonName;

    private final String seasonDesc;

    private EnumSeason(String seasonName, String seasonDesc) {
            this.seasonName = seasonName;
            this.seasonDesc = seasonDesc;
        }

        public String getSeasonName() {
            return seasonName;
        }

        public String getSeasonDesc() {
            return seasonDesc;
        }

        @Override
        public String toString() {
            return "EnumSeason{" +
                    "seasonName='" + seasonName + '\'' +
                    ", seasonDesc='" + seasonDesc + '\'' +
                    '}';
        }

    @Override
    public void show() {
        System.out.println("这是季节！");
    }
}
class SeasonTest{
    public static void main(String[] args) {
        Season fall = Season.FALL;
        System.out.println(fall);
        System.out.println(fall.getSeasonDesc());
        ////////////////////////////////////////
        EnumSeason spring = EnumSeason.SPRING;
        System.out.println(spring);
        System.out.println(spring.getSeasonDesc());
        System.out.println(EnumSeason.class.getSuperclass());
        System.out.println("************************");
        EnumSeason[] enumValues = EnumSeason.values();
        for (EnumSeason enumValue : enumValues) {
            System.out.println(enumValue);
            enumValue.show();
        }
        System.out.println("************************");
        Thread.State[] Threadvalues = Thread.State.values();
        for (Thread.State threadvalue : Threadvalues) {
            System.out.println(threadvalue);
        }
        System.out.println("************************");
        Thread.State aNew = Thread.State.valueOf("NEW");
        System.out.println(aNew);
    }
}