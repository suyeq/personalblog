package com.suye.personalblog.tool;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-05
 * Time: 14:55
 */
//用来转化文件名字,当名字重复时
public class FileNameConversion {

    public static String fileNameConversion(String name){
        int index=0;
        String newName=null;
        if (name.lastIndexOf("(")!=-1){
            index=name.lastIndexOf("(");
            char number=name.charAt(index+1);
            int num=Integer.parseInt(number+"")+1;
            newName=name.substring(0,index+1)+num+name.substring(index+2);
            System.out.println(newName );
            return newName;
        }else {
            index=name.lastIndexOf(".");
            newName=name.substring(0,index)+"(1)"+name.substring(index);
            System.out.println(newName);
            return newName;
        }
    }

}
