package com.suye.personalblog.tool;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-28
 * Time: 14:54
 */

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 线程安全的运行轨迹类
 */
public class RunningTrackStacks {

    public static void resetRuningTrack(HttpServletRequest request){
        List<RunningTrack> runningTrackList=getRunningTrackList(request);
        runningTrackList.clear();
        runningTrackList.add(new RunningTrack("首页","/index"));
        request.getSession().setAttribute("runningTrack",runningTrackList);
    }

    public static List<RunningTrack> getRunningTrackList(HttpServletRequest request){
        List<RunningTrack> runningTrackList=(List<RunningTrack>)request.getSession().getAttribute("runningTrack");
        if (runningTrackList==null){
            runningTrackList=new ArrayList<>();
        }
        request.getSession().setAttribute("runningTrack",runningTrackList);
        return runningTrackList;
    }

//    public void  setRunningTrackList(HttpServletRequest request){
//        request.getSession().setAttribute("runningTrack",);
//    }

    public static void addRunningTrack(HttpServletRequest request,String name,String url){
        boolean isContains=false;
        List<RunningTrack> runningTrackList=getRunningTrackList(request);
        Iterator<RunningTrack> iterator=runningTrackList.iterator();
        while (iterator.hasNext()){
            RunningTrack runningTrack=iterator.next();
            if (runningTrack.getName().equals(name)&&runningTrack.getUrl().equals(url))
                isContains=true;
        }
        if (!isContains){
            runningTrackList.add(new RunningTrack(name,url));
        }
        request.getSession().setAttribute("runningTrack",runningTrackList);
    }

    private  static  ThreadLocal<Set<RunningTrack>> runningStacks=new ThreadLocal<>();

    public static ThreadLocal<Set<RunningTrack>> getRunningStacks() {
        Set<RunningTrack> runningTrackSet=runningStacks.get();
        synchronized (RunningTrackStacks.class){
            if (runningTrackSet==null){
                runningTrackSet=new LinkedHashSet<>();
                runningStacks.set(runningTrackSet);
            }
        }
        return runningStacks;
    }

    public static Set<RunningTrack> getRunningTrackSet(){
        return getRunningStacks().get();
    }

    public static void addRunningTrack(String name,String url){
        boolean isContains=false;
        Set<RunningTrack> set=getRunningStacks().get();
        Iterator<RunningTrack> iterator=set.iterator();
        while (iterator.hasNext()){
            RunningTrack runningTrack=iterator.next();
            if (runningTrack.getName().equals(name)&&runningTrack.getUrl().equals(url))
                isContains=true;
        }
        if (!isContains){
            set.add(new RunningTrack(name,url));
        }
        //getRunningStacks().get().add(new RunningTrack(name,url));
    }

    public static void resetRuningTrack(){
        Set<RunningTrack> set=getRunningStacks().get();
        set.clear();
        System.out.println(set.size());
        set.add(new RunningTrack("首页","/index"));
    }

//    public static void setRunningStacks(ThreadLocal<Set<RunningTrack>> runningStacks) {
//        RunningTrackStacks.runningStacks = runningStacks;
//    }




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
