package com.suye.personalblog.FileClient.handler;


import com.suye.personalblog.PersonalblogApplication;
import com.suye.personalblog.model.FileUploadFile;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件上传处理
 */
public class FileUploadClientHandler extends ChannelInboundHandlerAdapter {
    //private
    private int byteRead;
    private volatile int start = 0;
    private volatile int lastLength = 0;
    public RandomAccessFile randomAccessFile;
    private FileUploadFile fileUploadFile;
    public FileUploadClientHandler(FileUploadFile ef) {
        if (ef.getFile().exists()) {
            if (!ef.getFile().isFile()) {
                System.out.println("Not a file :" + ef.getFile());
                return;
            }
        }
        this.fileUploadFile = ef;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("客户端结束传递文件channelInactive()");
    }

    public void channelActive(ChannelHandlerContext ctx) {
        //LOGGER.info("正在执行channelActive()方法.....");
        System.out.println("正在执行channelActive()方法.....");
        try {
            randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(),
                    "r");
            randomAccessFile.seek(fileUploadFile.getStarPos());
            // lastLength = (int) randomAccessFile.length() / 10;
            lastLength = 1024 *1024;
            byte[] bytes = new byte[lastLength];
            if ((byteRead = randomAccessFile.read(bytes)) != -1) {
                fileUploadFile.setEndPos(byteRead);
                fileUploadFile.setBytes(bytes);
                ctx.writeAndFlush(fileUploadFile);//发送消息到服务端
            } else {
            }
            System.out.println("channelActive()文件已经读完 "+byteRead);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
        System.out.println("channelActive()方法执行结束");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        if (msg instanceof Integer) {
            start = (Integer) msg;
            if (start != -1) {
                randomAccessFile = new RandomAccessFile(fileUploadFile.getFile(), "r");
                randomAccessFile.seek(start); //将文件定位到start
                System.out.println("长度：" + (randomAccessFile.length() - start));
                int a = (int) (randomAccessFile.length() - start);
                int b = (int) (randomAccessFile.length() / 1024 * 2);
                if (a < lastLength) {
                    lastLength = a;
                }
                System.out.println("文件长度：" + (randomAccessFile.length()) + ",start:" + start + ",a:" + a + ",b:" + b + ",lastLength:" + lastLength);
                byte[] bytes = new byte[lastLength];
                System.out.println("bytes的长度是="+bytes.length);
                if ((byteRead = randomAccessFile.read(bytes)) != -1 && (randomAccessFile.length() - start) > 0) {
                    System.out.println("byteRead = "  + byteRead);
                    fileUploadFile.setEndPos(byteRead);
                    fileUploadFile.setBytes(bytes);
                    try {
                        ctx.writeAndFlush(fileUploadFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    randomAccessFile.close();
                    ctx.close();
                    System.out.println("文件已经读完channelRead()--------" + byteRead);
                }
            }
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


}
