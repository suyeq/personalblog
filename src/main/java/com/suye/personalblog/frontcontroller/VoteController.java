package com.suye.personalblog.frontcontroller;

import com.suye.personalblog.service.BlogService;
import com.suye.personalblog.service.ConmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2018-12-28
 * Time: 11:26
 */
@Controller
public class VoteController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private ConmentService conmentService;


    @RequestMapping("/vote")
    @ResponseBody
    public int increaseVotenum(@RequestParam("action")String action,
                               @RequestParam("um_id")String Id){
        if (action.equals("blog_zan")){
            blogService.increaseVotenum(Integer.parseInt(Id));
            return blogService.findOneById(Integer.parseInt(Id)).getVotenum();
        }else if (action.equals("conment_zan")){
            conmentService.increaseConmentZan(Integer.parseInt(Id));
            return conmentService.findOneConmentById(Integer.parseInt(Id)).getVotenum();
        }else if (action.equals("conment_cai")){
            conmentService.increaseConmentCai(Integer.parseInt(Id));
            return conmentService.findOneConmentById(Integer.parseInt(Id)).getCainum();
        }
        return 0;
    }
}
