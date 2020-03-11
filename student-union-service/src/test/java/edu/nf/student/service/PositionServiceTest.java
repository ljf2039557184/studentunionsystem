package edu.nf.student.service;

import edu.nf.student.entity.Position;
import edu.nf.student.service.config.AppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class PositionServiceTest {

    @Test
    public void listPosition() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PositionService service = context.getBean(PositionService.class);
        List<Position> list = service.listPosition();
        for (Position position : list) {
            System.out.println(position.getStuPosition());
        }
    }
}