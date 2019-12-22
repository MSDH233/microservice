package com.fujinran.utils;

import com.fujinran.utils.bean.Mail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.HtmlEmail;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@Slf4j
public class MailUtils {
    public static boolean send(Mail mail){
        String from = "jinran.fu@hand-china.com" ;
        int port = 25 ;
        String host = "smtphm.qiye.163.com";
        String pass = "FJA9636307{}";
        String nickName = "付锦然";
        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for(String receiver : mail.getReceivers()){
                email.addTo(receiver);
            }
            email.setFrom(from,nickName);
            email.setSmtpPort(port);
            email.setAuthentication(from,pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {} 成功",from, StringUtils.join(mail.getReceivers(),","));
            return true ;
        }catch (Exception e){
            log.info("{} 发送邮件到 {} 失败",from, StringUtils.join(mail.getReceivers(),","));
            return false ;
        }
    }
}