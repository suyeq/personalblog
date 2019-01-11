package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.StaticField;
import com.suye.personalblog.email.SimpleSMTPEmailSend;
import com.suye.personalblog.model.Conment;
import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.ConmentService;
import com.suye.personalblog.service.VisitorService;
import com.suye.personalblog.tool.BlogMessageConversion;
import com.suye.personalblog.tool.ConmentMessageConversion;
import com.suye.personalblog.tool.SensitiveWordInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-26
 * Time: 20:21
 */
@Controller
public class ConmentController {

    @Autowired
    private ConmentMessageConversion conmentMessageConversion;
    @Autowired
    private ConmentService conmentService;
    @Autowired
    private VisitorService visitorService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogMessageConversion blogMessageConversion;
    @Autowired
    private SimpleSMTPEmailSend simpleSMTPEmailSend;
    @Autowired
    private SensitiveWordInit sensitiveWordInit;

    @PostMapping("/addConmentAndVisitor")
    public ModelAndView addConment(@RequestParam("author")String author,
                                   @RequestParam("email")String email,
                                   @RequestParam("url")String url,
                                   @RequestParam("comment_post_ID")String blogId,
                                   @RequestParam("comment_parent")String newConmentParentId,
                                   @RequestParam("comment") String conment,
                                   Model model) throws IOException {
//        System.out.println("addConment");
//        System.out.println(author+" "+email+" "+url+" "+blogId+" "+newConmentParentId+" "+conment);
        blogService.increaseConmentNum(Integer.parseInt(blogId));
        if (!visitorService.isHaveThisVisitor(email)){
            visitorService.addVisitor(author,email,url);
        }
        //sensitiveWordInit.init();
        conment=sensitiveWordInit.replaceSensitiveWord(conment, StaticField.SENSITIVE_WORD);
        conmentService.addConment(conment,visitorService.findOneByEmail(email).getId(),
                        Integer.parseInt(blogId),Integer.parseInt(newConmentParentId));
        //System.out.println("进来啦啦啦啦");
       // System.out.println("allalal"+conmentService.findEmailByCommentId(Integer.parseInt(newConmentParentId)));
//        if (Integer.parseInt(newConmentParentId)!=0){
//            simpleSMTPEmailSend.sendMessage(email,conmentService.findEmailByCommentId(Integer.parseInt(newConmentParentId)),conment);
//        }else {
//            simpleSMTPEmailSend.sendMessage(email,conment);
//        }
        List<Conment> conments=new ArrayList<>();
        conments.add(conmentService.findLastConment());
        List<ConmentMessageConversion.ConmentMessage> newConmentList
                =conmentMessageConversion.conmentMessageList(conments);
        model.addAttribute("newConmentList",newConmentList);
        model.addAttribute("blogId",Integer.parseInt(blogId));
        return new ModelAndView("front/addconment","newConment",model);
    }

    @GetMapping("/loadMore/{blogId}/{page}")
    public ModelAndView loadMoreConment(@PathVariable("blogId")String blogId,
                                        @PathVariable("page")String page,
                                        Model model){
        System.out.print(blogId+page);
        List<ConmentMessageConversion.ConmentMessage> recentAllConmentList=
                conmentMessageConversion.findAllConmentsByBlogId(Integer.parseInt(blogId),(conmentMessageConversion.conmentPages(conmentService.conmnetTotal(Integer.parseInt(blogId)))-Integer.parseInt(page))*5);
        List<Object> conmentPages=conmentMessageConversion.conversionTotal(conmentService.conmnetTotal(Integer.parseInt(blogId)));

        BlogMessageConversion.BlogMessage blogMessage=
                blogMessageConversion.getOneBlogMessage(blogService.findOneById(Integer.parseInt(blogId)));
        model.addAttribute("recentAllConmentList",recentAllConmentList);
        model.addAttribute("conmentPages",conmentPages);
        model.addAttribute("blogDatailsMessage",blogMessage);
        return new ModelAndView("front/loadmoreconment","blogMessage",model);
    }





}
