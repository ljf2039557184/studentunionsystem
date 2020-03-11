package edu.nf.student.dao;

import edu.nf.student.entity.Student;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author admin
 * @date 2019/11/19
 * 学生信息表
 */
public interface StudentDao {
    /**
     * 获取所有学生信息
     * @return
     */
    List<Student> listStudentInfo(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);


    Student getStudentInfo(String s);

    Student getStudentLogin(String stuTel);

    void updateStudentImage(Student s);

    void updateStudentInfo(Student s);

    void addStudentInfo(Student s);

    List<Student> getStudentFind(String findValue);


    void deleteStudentId(Integer sid);



}