package com.suye.personalblog.FileServer.handler;

import com.suye.personalblog.model.FileUploadFile;
import com.suye.personalblog.service.FileService;
import com.suye.personalblog.BeanFactory;
import com.suye.personalblog.tool.FileNameConversion;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;

/**
 文件上传服务端处理
 */
public class FileUploadServerHandler extends ChannelInboundHandlerAdapter {

    private FileService fileService;
    private int byteRead;
    private volatile int start = 0;
    private String name;
    private int fileId;
    String finalUrl="http://localhost:8888/img/";
    private String file_dir="D://codebiji//Tomcat//apache-tomcat-9.0.7//webapps//img";
    private String file_temp="D://codebiji//Tomcat//apache-tomcat-9.0.7//webapps//temp";
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    	super.channelActive(ctx);
        //LOGGER.info("服务端：channelActive()");
        System.out.println("服务端：channelInactive()");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	super.channelInactive(ctx);
        System.out.println("服务端：channelInactive()");
    	ctx.flush();
    	ctx.close();
    	//手动获取bean类
        FileService fileService=BeanFactory.getApplicationContext().getBean(FileService.class);
        String fileName=fileService.findOneById(fileId).getName();
        File file=new File(file_temp,fileName);
        System.out.println(file.getAbsoluteFile());
        //防止文件未删除
//        boolean result = false;
//        int tryCount = 0;
//        while(!result && tryCount++ <10)
//        {
//            System.gc();
//            result = file.delete();
//        }
        fileService.updateFileUrl(fileId,finalUrl+name);
//        if (file.exists()){
//            file.delete();
//        }
        //防止文件未删除
        boolean result = false;
        int tryCount = 0;
        while(!result && tryCount++ <10)
        {
            System.gc();
            result = file.delete();
        }
        System.out.println(result);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到客户端发来的文件,正在处理....");
        if (msg instanceof FileUploadFile) {
            FileUploadFile ef = (FileUploadFile) msg;
            byte[] bytes = ef.getBytes();
            byteRead = ef.getEndPos();
            String md5 = ef.getFile_md5();//文件名
            fileId=ef.getFileId();
            name=md5;
            String path = file_dir + File.separator + name;
            File file = new File(path);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");//r: 只读模式 rw:读写模式
            randomAccessFile.seek(start);//移动文件记录指针的位置,
            randomAccessFile.write(bytes);//调用了seek（start）方法，是指把文件的记录指针定位到start字节的位置。也就是说程序将从start字节开始写数据
            start = start + byteRead;
            if (byteRead > 0) {
                ctx.writeAndFlush(start);//向客户端发送消息
                randomAccessFile.close();
                if(byteRead!=1024 * 1024){
                	//Thread.sleep(1000);
                	channelInactive(ctx);
                }
            } else {
                ctx.close();
            }
            System.out.println("处理完毕,文件路径:"+path+","+byteRead);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
        System.out.println("FileUploadServerHandler--exceptionCaught()");
    }

}
