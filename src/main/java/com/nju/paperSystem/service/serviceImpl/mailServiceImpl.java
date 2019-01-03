package com.nju.paperSystem.service.serviceImpl;

import com.nju.paperSystem.config.emailConfig;
import com.nju.paperSystem.entity.email;
import com.nju.paperSystem.entity.modification;
import com.nju.paperSystem.entity.student;
import com.nju.paperSystem.service.mailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class mailServiceImpl implements mailService {
    @Autowired
    emailConfig mc;

    @Override
    public void sendEmail(student student, modification modification,String fileName) throws MessagingException, IOException {
        email studentemail = new email();
        email teacheremail = new email();
        //给学生发送邮件提醒
        studentemail.setReceiver(student.getStudentEmail());
        studentemail.setContent(modification.getSummary()+"\n"+modification.getDescription());
        studentemail.setSubject(student.getStudentName()+"-版本"+modification.getVersion()+"-"+modification.getDate()+"-学位论文");
        //给老师发送邮件提醒
        teacheremail.setReceiver(student.getTeacherEmail());
        teacheremail.setContent(modification.getSummary()+"\n"+modification.getDescription());
        teacheremail.setSubject(student.getStudentName()+"-版本"+modification.getVersion()+"-"+modification.getDate()+"-学位论文");
        mc.sendMail(studentemail, student.getStudentEmail(), fileName);
        mc.sendMail(teacheremail, student.getStudentEmail(), fileName);
        System.out.println("successful to send message!");
    }
}