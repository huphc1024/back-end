package com.report.utils;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.report.entity.User;

@Service
public class SendMail {
    private JavaMailSender javaMailSender;
    
    @Autowired
    public SendMail(JavaMailSender javaMailSender) {
            this.javaMailSender = javaMailSender;
    }

    public void send(User user, String password) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo(user.getEmail());
        helper.setSubject("Chào mừng bạn đến với DailyReport");
        helper.setText("<!DOCTYPE html><html><body><p>Chào mừng bạn "+ user.getFullname() + "! \r\n" + 
                "  <br>Tài khoản của bạn là:\r\n" + 
                "</p>\r\n" + 
                "<p>Username: \r\n" + 
                "  <a href=\"mailto:"+ user.getEmail() +"\">"+ user.getEmail() +"</a>\r\n" + 
                "  <br>Password: "+ password + "\r\n" + 
                "  <br>\r\n" + 
                "  <br>\r\n" + 
                "</p></body></html>", true);

        javaMailSender.send(msg);
    }
    
    public void sendMailAddProject(List<User> users, String projectName) throws MessagingException {
        for (User user : users) {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Thông báo về dự án mới");
            helper.setText("<!DOCTYPE html><html><body><p>Chào mừng bạn "+ user.getFullname() + "! \r\n" + 
                    "  <br>Chào bạn vào dự án :\r\n" + projectName +
                    "</p>\r\n" + 
                    "</p></body></html>", true);

            javaMailSender.send(msg);
        }
        
    }
    
    public void sendMailAddTask(User user, String taskName, String password) throws MessagingException {
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(user.getEmail());
            helper.setSubject("Thông báo về Công việc mới");
            helper.setText("<!DOCTYPE html><html><body><p>Chào mừng bạn "+ user.getFullname() + "! \r\n" + 
                    "  <br>Công việc bạn được phân công là :\r\n" + taskName +
                    "</p>\r\n" + 
                    "  <br>\r\n" + 
                    "</p></body></html>", true);

            javaMailSender.send(msg);
        
    }
}
