package edu.nf.student.controller;

import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.entity.Department;
import edu.nf.student.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/5
 */
@RestController
public class DepartmentInfoController extends BaceControler {

    private final static Logger logger = LoggerFactory.getLogger(DepartmentInfoController.class);


    @Autowired
    private DepartmentService service;

    @GetMapping("/list_department")
    public ResponseVO listDepartment(){

        List<Department> list = service.listDepartment();
        return success(list);
    }
}