package edu.nf.student.dao;

import edu.nf.student.entity.Department;

import java.util.List;

/**
 * 部门表
 * @author admin
 * @date 2019/12/5
 */
public interface DepartmentDao {

    List<Department> listDepartment();
}