package com.suye.personalblog.admincontroller;


import com.suye.personalblog.StaticField;
import com.suye.personalblog.email.SimpleSMTPEmailSend;
import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.LogMessageService;
import com.suye.personalblog.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-03
 * Time: 14:31
 */
@Controller
public class AdminCommentController {
    @Autowired
    private ConmentService conmentService;
    @Autowired
    private SimpleSMTPEmailSend simpleSMTPEmailSend;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private LogMessageService logMessageService;
    @RequestMapping("/admin/comments/delete")
    @ResponseBody
    public String deleteConment(@RequestParam("coid")int id){
        if (conmentService.deleteCommentById(id)>0){
            logMessageService.addALog(StaticField.DELETE_COMMENT);
            return "{\n" + "  \"success\":\"保存成功\"\n" + "}";
        }
        return "{\n" + "  \"msg\":\"删除异常\"\n" + "}";
    }

    /**
     * {coid: coid, content: comment,blogid:blogid},
     * @return
     */
    @RequestMapping("/admin/comments/recom")
    @ResponseBody
    public String replyConment(@RequestParam("coid")int parentId,
                               @RequestParam("content")String content,
                               @RequestParam("blogid")int blogid){
        if (conmentService.addConment(content,1,blogid,parentId)>0){

            String email=visitorService.findOneById(conmentService.findOneConmentById(parentId).getVisitor_id()).getEmail();
            simpleSMTPEmailSend.sendMessage(email,content);
            logMessageService.addALog(StaticField.REPLY_COMMENT);
            return "{\n" + "  \"success\":\"回复成功\"\n" + "}";
        }else {
            return "{\n" + "  \"msg\":\"删除异常\"\n" + "}";
        }
    }
}
