package edu.nf.student.service;

import edu.nf.student.entity.Department;
import edu.nf.student.entity.Position;
import edu.nf.student.service.config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class DepartmentServiceTest {

    @Test
    public void listDepartment() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DepartmentService service = context.getBean(DepartmentService.class);
        List<Department> list = service.listDepartment();
        for (Department department : list) {
            System.out.println(department.getDepartment());
        }
    }
}