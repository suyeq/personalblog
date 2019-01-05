package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.FileClient.work.FileUploadClient;
import com.suye.personalblog.model.Conment;
import com.suye.personalblog.model.FileUploadFile;
import com.suye.personalblog.model.Label;
import com.suye.personalblog.model.Visitor;
import com.suye.personalblog.service.*;
import com.suye.personalblog.tool.ConmentMessageConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-20
 * Time: 13:43
 */
@Controller
public class Hello {
    private static final int FILE_PORT = 9991;
    @Autowired
    private VisitorService visitorService;

    @Autowired
    private LabelService labelService;

    @Autowired
    private ConmentService conmentService;
    @Autowired
    private BlogService blogService;
    @Autowired
    ConmentMessageConversion conmentMessageConversion;
    @Autowired
    private FileService fileService;


    @GetMapping("/visitor")
    @ResponseBody
    public int getAlls(){

       return 1;
    }

    @GetMapping("/label")
    @ResponseBody
    public int getAll(){
        System.out.println("lalalla");
        try {
            FileUploadFile uploadFile = new FileUploadFile();
            File file = new File("F:\\Windows 7 x64\\Windows 7 x64-s003.vmdk");//
            String fileMd5 = file.getName();// 文件名
            uploadFile.setFile(file);
            uploadFile.setFile_md5(fileMd5);
            uploadFile.setStarPos(0);// 文件开始位置
            new FileUploadClient().connect(FILE_PORT, "127.0.0.1", uploadFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    @GetMapping("/conment")
    public List<Conment> getAllConment(){
        List<Conment> list=conmentService.recentConment();
        for (int i=0;i<list.size();i++){
            System.out.println(new Date(list.get(i).getConment_time().getTime()-8*60*60*1000));
        }
        return list;
    }

    @RequestMapping("/con")
    public void showConmentChild(){
        //conmentMessageConversion.findAllConmentsByBlogId(1);
    }

    @GetMapping("/blog")
    public String getAllblog(){
        return blogService.findOneById(1).getContent();
    }

}
