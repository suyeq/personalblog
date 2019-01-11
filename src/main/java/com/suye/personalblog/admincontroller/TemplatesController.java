package com.suye.personalblog.admincontroller;

import com.suye.personalblog.StaticField;
import com.suye.personalblog.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-07
 * Time: 19:05
 */
@Controller
public class TemplatesController {
    @Value("${templates.path}")
    private String templatePath;
    @Autowired
    private LogMessageService logMessageService;

    @RequestMapping("/admin/template/content")
    @ResponseBody
    public String getTemplateContent(@RequestParam("fileName")String fileName) throws IOException {
        File file=new File(templatePath,fileName);
        BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
        String line="";
        String content="";
        while ((line=bufferedReader.readLine())!=null){
            content+=line+"\n";
            //bufferedWriter.write(line+"\n");
        }
        //bufferedWriter.flush();
       // bufferedWriter.close();
        bufferedReader.close();
        //System.out.println(file.getAbsoluteFile());
        return content;
    }

    @RequestMapping("/admin/template/save")
    @ResponseBody
    public String saveTemplateContent(@RequestParam("fileName")String fileName,
                                      @RequestParam("content")String content) throws IOException {
        File file=new File(templatePath,fileName);
        File newFile=new File(templatePath,fileName);
        BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(newFile));
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
        logMessageService.addALog(StaticField.MODIFY_PAGE);
        return "{\n" + "  \"success\":\"保存成功\"\n" + "}";
    }
}
