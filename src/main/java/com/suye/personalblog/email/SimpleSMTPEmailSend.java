package com.suye.personalblog.email;

import com.suye.personalblog.tool.ConmentMessageConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * Author: Suyeq
 * Date: 2019-01-06
 * Time: 11:28
 */
@Component
public class SimpleSMTPEmailSend {

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${email.user}")
    private String emialSend;


    public void sendMessage(String receiveEmail,String content){

        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        if (javaMailSender==null){
            System.out.println("jsjshsh");
        }
        System.out.println(emialSend);
        message.setFrom(emialSend);//发件人
        message.setTo(receiveEmail);//收件人
        message.setSubject("一两清风，半盏明月");//主题
        message.setText(content);//正文
        new Thread(new Runnable() {
            @Override
            public void run() {
                javaMailSender.send(message);
            }
        }).start();
        System.out.println("邮件发送毕");
    }

    public void sendMessage(String sendEmail,String receiveEmail,String content){
        SimpleMailMessage message = new SimpleMailMessage();//消息构造器
        message.setFrom(sendEmail);//发件人
        message.setTo(receiveEmail);//收件人
        message.setSubject("一两清风，半盏明月");//主题
        message.setText(content);//正文
        new Thread(new Runnable() {
           @Override
           public void run() {
               javaMailSender.send(message);
           }
       }).start();
        System.out.println("邮件发送完");
    }
}
