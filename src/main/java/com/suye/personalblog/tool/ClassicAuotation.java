package com.suye.personalblog.tool;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-09
 * Time: 11:33
 */
//一言与验证码
public class ClassicAuotation {

    private static String[] auotation={
            "我重临世界之日，诸逆臣皆当死去。",
            "到底什么是真正的幸福呢，我也说不清楚。其实，无论遇到多么痛苦艰难的事，就算爬高山也好，下斜坡也好，只要能走正确的道路，就能一步步接近幸福。",
            "我们都是小怪兽，都会被正义的奥特曼杀死。",
            "或许是不知梦的缘故，流离之人追逐幻影。",
            "你陪了我多少年，花开花落。一路上起起跌跌！",
            "一切伟大时代皆会结束，正如所有的王都将死去。",
            "没有人逃得过悲伤，悲伤才是最大的魔鬼。",
            "命运这种东西，生来就是要被踏于足下的，如果你还未有力量反抗它，只需怀着勇气等待。",
            "每个人心里都有一个魔鬼，幸福是他的牢笼，当人们的幻想化作泡影，恶魔将唱着血腥的圣歌降临，那时绝望的人将所向无敌！"
    };

    public static String getAAuotation(){
        int length=auotation.length;
        Random random=new Random();
        int index=random.nextInt(length);
        return auotation[index];
    }

    public static  int getANum(){
        Random random=new Random();
        return random.nextInt(20);
    }

    public static String[] getAuotation() {
        return auotation;
    }

    public static void setAuotation(String[] auotation) {
        ClassicAuotation.auotation = auotation;
    }
}
