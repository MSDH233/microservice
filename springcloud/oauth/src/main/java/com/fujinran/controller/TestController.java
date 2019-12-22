package com.fujinran.controller;

import com.fujinran.utils.MailUtils;
import com.fujinran.utils.bean.Mail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("/send_message")
    public boolean sendMessage(){
        Set<String> receivers = new HashSet<>();
        receivers.add("liqin.yan@hand-china.com");
        receivers.add("zhe.zhang04@hand-china.com");
        Mail mail = Mail.builder().message("测试邮件").receivers(receivers).subject("测试邮件").build();
        return MailUtils.send(mail);
    }
}