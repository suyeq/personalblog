package com.suye.personalblog.admincontroller;

import com.suye.personalblog.FileClient.task.UploadTask;
import com.suye.personalblog.StaticField;
import com.suye.personalblog.service.FileService;
import com.suye.personalblog.service.LogMessageService;
import com.suye.personalblog.tool.FileNameConversion;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiFileChooserUI;
import javax.validation.constraints.Max;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-04
 * Time: 14:31
 */
@Controller
public class FileController {

   // ExecutorService threadPool=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    @Value("${img.temp.address}")
    private String temp;
    @Value("${img.temp.server}")
    private String tempIp;
    @Value("${img.address}")
    private String finalIp;

    @Autowired
    private FileService fileService;
    @Autowired
    private LogMessageService logMessageService;

    @RequestMapping("/admin/attach/delete")
    @ResponseBody
    public String deleteFile(@RequestParam("id")int id){
        //System.out.println("123");
        if (fileService.deleteFileById(id)>0){
//            String name=fileService.findOneById(id).getName();
//            File fileTemp=new File(temp,name);
//            if (fileTemp.exists()){
//                fileTemp.delete();
//            }
            logMessageService.addALog(StaticField.DELETE_FILE);
            return "{\n" + "  \"success\":\"删除成功\"\n" + "}";
        }
        return "{\n" + "  \"msg\":\"删除异常\"\n" + "}";
    }

    @RequestMapping("/admin/attach/upload")
    @ResponseBody
    public String uploadFile(MultipartFile file) throws IOException {
        //byte []b=file.getBytes();
        //System.out.println(file.getOriginalFilename());
        String origName=file.getOriginalFilename();
        File imgFile=new File(temp,origName);
        while (imgFile.exists()){
            origName=FileNameConversion.fileNameConversion(origName);
            imgFile=new File(temp,origName);
        }
        if (!imgFile.getParentFile().exists()){
            imgFile.getParentFile().mkdirs();
        }
        System.out.println(imgFile.getAbsoluteFile());
        OutputStream outputStream=new FileOutputStream(imgFile);
        InputStream inputStream=file.getInputStream();
        IOUtils.copy(file.getInputStream(),outputStream);
        outputStream.flush();
        fileService.addFile(imgFile.getName(),tempIp+imgFile.getName());
        //threadPool.submit(new UploadTask(imgFile));
        int fileId=fileService.findFileIdByFileName(imgFile.getName());
        new UploadTask(imgFile,fileId).run();
        outputStream.close();
        inputStream.close();
        //System.out.println("kakkakak");
        logMessageService.addALog(StaticField.ADD_FILE);
        return "{\n" + "  \"success\":\""+tempIp+imgFile.getName()+"\"\n" + "}";
    }
}
