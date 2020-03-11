package edu.nf.student.service;

import edu.nf.student.entity.Student;
import edu.nf.student.service.config.AppConfig;
import edu.nf.student.service.util.DateUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.xml.crypto.Data;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class StudentServiceTest {

    @Test
    public void listStudentInfo() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//        StudentService service = context.getBean("studentService",StudentService.class);
//        List<Student> list = service.listStudentInfo();
//        for (Student student : list) {
//            System.out.println(student.getStuName());
//            System.out.println(student.getPosition().getStuPosition());
//            System.out.println(student.getDepartment().getDepartment());
//        }
    }

    @Test
    public void studentLogin() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService service = context.getBean("studentService",StudentService.class);
        Student s = service.studentLogin("18218337212","123456");
        System.out.println(s.getStuName());
        System.out.println(s.getStuTel());
        System.out.println(s.getPassWord());



    }

    @Test
    public void getStudentFind() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentService service = context.getBean("studentService",StudentService.class);
        String words ="2019年10月11日";
        String newStr = words.replace("年","-").replace("月","-")
                .replace("日","");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");


        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd" );
        Date d1= new Date();
        String str1 = sdf1.format(d1);

        System.out.println("当前时间通过 yyyy-MM-dd 格式化后的输出: "+str1);

//        System.out.println(date.getTime());
    }
}