package edu.nf.student.dao;

import edu.nf.student.dao.config.DaoConfig;
import edu.nf.student.entity.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class StudentDaoTest {

    @Test
    public void listStudentInfo() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
//        StudentDao s = context.getBean(StudentDao.class);
//        List<Student> list = s.listStudentInfo();
//        for (Student student : list) {
//            System.out.println(student.getStuName());
//            System.out.println(student.getPosition().getStuPosition());
//            System.out.println(student.getDepartment().getDepartment());
//        }
    }

//    @Test
//    public void getStudentInfo() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
//        StudentDao s = context.getBean(StudentDao.class);
//        Student ss = new Student();
//        ss.setStuTel("18218337212");
//        Student  sty= s.getStudentInfo(ss);
//        System.out.println(sty.getStuName());
//    }

    @Test
    public void getStudentLogin() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class);
        StudentDao s = context.getBean("studentDao",StudentDao.class);
        String tel = "18218337212";
        Student  sty= s.getStudentLogin(tel);
        System.out.println(sty.getStuName());
    }
}