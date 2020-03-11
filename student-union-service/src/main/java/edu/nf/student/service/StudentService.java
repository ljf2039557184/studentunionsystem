package edu.nf.student.service;

import com.github.pagehelper.PageInfo;
import edu.nf.student.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author admin
 * @date 2019/11/19
 */


public interface StudentService {
    /**
     * 所有学生信息
     * @return
     */
    PageInfo<Student> listStudentInfo(Integer pageNum, Integer pageSize);

    Student getStudnetInfo(String sid);

    Student studentLogin(String stuTel,String passWord);

    /**
     * 修改图片
     * @param s
     */
    void updateStudentImage(Student s);


    /**
     * 模糊查询
     */
    List<Student> getStudentFind(String findValue);

    /**
     * 根据ID删除
     * @param sid
     */
    void deleteStudentId(Integer sid);


    /**
     * 添加学生
     * @param s
     */
    void addStudentInfo(Student s);
}