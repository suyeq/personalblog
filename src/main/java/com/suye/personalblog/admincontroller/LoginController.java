package com.suye.personalblog.admincontroller;


import com.suye.personalblog.StaticField;
import com.suye.personalblog.service.LogMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-01
 * Time: 15:48
 */
@Controller
public class LoginController {
    @Value("${admin.name}")
    private String name;
    @Value("${admin.password}")
    private String password;
    @Autowired
    private LogMessageService logMessageService;

    @RequestMapping("/admin/logins")
    public ModelAndView login(Model model){
        return new ModelAndView("/admin/login","login",model);
    }

    @RequestMapping("/admin/login")
    @ResponseBody
    public String loginVerification(HttpServletRequest request, @RequestParam("username")String username,
                                    @RequestParam("password")String password, Model model){
        System.out.println(username+password);
        if (username.equals(name)&&password.equals(password)){
            request.getSession().setAttribute("isLogin","success");
            logMessageService.addALog(StaticField.LOGIN_ACTION);
            return "{\n" + "  \"success\":\"1\"\n" + "}";
        }
        return "{\n" + "  \"msg\":\"账户或密码错误\"\n"+"}";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        logMessageService.addALog(StaticField.LOGOUT_ACTION);
        return "redirect:/index";
    }

}
