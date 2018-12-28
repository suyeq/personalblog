package com.suye.personalblog.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-25
 * Time: 12:39
 */

/**
 * 获取运行轨迹的工具类
 */
public class RunningTrackStack {

//    private final static int BLOGS=1<<8-1;
//    private final static byte SHUOSHUOS=1<<5-1;
  //  private static byte state=0;

    private static List<RunningTrack> runningTrackStack=new ArrayList<>();

    public static void addRunningStack(String name,String url){
        runningTrackStack.add(new RunningTrack(name,url));
    }

    public static void addRunningStack(int defference,String name,String url){
        if (defference==1){
            for (int i=0;i<runningTrackStack.size();i++){
                if (runningTrackStack.get(i).getName().equals("博客")){
                    runningTrackStack.remove(i);
                    break;
                }
            }
        }else if (defference==0){
            for (int i=0;i<runningTrackStack.size();i++){
                if (runningTrackStack.get(i).getName().equals("说说")){
                    runningTrackStack.remove(i);
                    break;
                }
            }
        }
        runningTrackStack.add(new RunningTrack(name,url));
    }

    public static List<RunningTrack> getRunningTrackStack() {
        return runningTrackStack;
    }


    public static void clearRunningTrackStack(){
        runningTrackStack.clear();
//        state=0;
        runningTrackStack.add(new RunningTrack("首页","/index"));
    }

    public static class RunningTrack{

        private String name;

        private String url;

        public RunningTrack(String name,String url){
            this.name=name;
            this.url=url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
