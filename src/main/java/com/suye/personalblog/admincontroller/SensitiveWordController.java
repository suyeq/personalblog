package com.suye.personalblog.admincontroller;

import com.suye.personalblog.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-09
 * Time: 9:50
 */
@Controller
public class SensitiveWordController {

    @Autowired
    private SensitiveService sensitiveService;

    @RequestMapping("/admin/sensitiveword/save")
    @ResponseBody
    public String addSensitiveWord(@RequestParam("cname")String word){
        if (!sensitiveService.addSensitiveWord(word)){
            return "{\n" + "  \"msg\":\"敏感词已存在\"\n" + "}";
        }
        return "{\n" + "  \"success\":\"增加成功\"\n" + "}";
    }

    @RequestMapping("/admin/sensitiveword/delete")
    @ResponseBody
    public String deleteSensitiveWord(@RequestParam("cname")String word){
        sensitiveService.deleteSensitiveWord(word);
        return "{\n" + "  \"success\":\"增加成功\"\n" + "}";
    }
}
