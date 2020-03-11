package edu.nf.student.service.impl;

import edu.nf.student.dao.DepartmentDao;
import edu.nf.student.entity.Department;
import edu.nf.student.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/12/5
 */
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentDao dao;
    @Override
    public List<Department> listDepartment() {
        return dao.listDepartment();
    }
}