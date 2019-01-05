package com.suye.personalblog.FileClient.task;

import com.suye.personalblog.FileClient.work.FileUploadClient;
import com.suye.personalblog.model.FileUploadFile;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-05
 * Time: 16:19
 */
//上传任务
public class UploadTask implements Runnable{

    private String ip="127.0.0.1";
    private int port=9991;
//    private static final int FILE_PORT = 9991;
    private File file;
    private int fileId;
    //private String fileName;

    public  UploadTask(File file,int fileID){
        this.file=file;
        this.fileId=fileID;
        //this.fileName=fileName;
    }

    @Override
    public void run() {
        FileUploadFile uploadFile = new FileUploadFile();
        String fileMd5 = file.getName();// 文件名
        uploadFile.setFileId(fileId);
        System.out.println(fileMd5+"llll");
        uploadFile.setFile(file);
        uploadFile.setFile_md5(fileMd5);
        uploadFile.setStarPos(0);// 文件开始位置
        try {
            new FileUploadClient().connect(port, ip, uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
