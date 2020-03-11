package edu.nf.student.service.impl;

import com.github.pagehelper.PageInfo;
import edu.nf.student.dao.StudentDao;
import edu.nf.student.entity.Student;
import edu.nf.student.service.StudentService;
import edu.nf.student.service.exception.DataAccessException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @author admin
 * @date 2019/11/20
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao dao;


    /**
     * 所有信息
     * @return
     */
    @Override
    public  PageInfo<Student> listStudentInfo( Integer pageNum,  Integer pageSize) {
        PageInfo<Student> pageInfo = null;

        try {
            List<Student> list = dao.listStudentInfo(pageNum,pageSize);
            pageInfo = new PageInfo<>(list);

            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("服务器内部错误，暂时无法访问");
        }

    }


    /**
     * 根据Id查询信息
     * @param s
     * @return
     */
    @Override
    public Student getStudnetInfo(String s) {
        return dao.getStudentInfo(s);
    }

    /**
     * 登录
     * @return
     */
    @Override
    public Student studentLogin(String stuTel,String password) {

             Student stu = dao.getStudentLogin(stuTel);
             System.out.println(password);
             System.out.println(stu.getPassWord());
             if(stu==null){
                 throw new DataAccessException("账号不存在");
             }
             if(password.equals(stu.getPassWord())){
                 return stu;
             }
            throw new DataAccessException("账号或密码错误");





    }

    /**
     * 修改图片
     * @param s
     */
    @Override
    public void updateStudentImage(Student s) {
        dao.updateStudentImage(s);
    }


    /**
     * 模糊查询
     * @param findValue
     * @return
     */
    @Override
    public List<Student> getStudentFind(String findValue) {

        return dao.getStudentFind(findValue);
    }


    /**
     * 根据ID删除
     * @param sid
     */
    @Override
    public void deleteStudentId(Integer sid) {
        try {
            dao.deleteStudentId(sid);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("删除失败");
        }
    }


    /**
     * 录入信息
     * @param s
     */
    @Override
    public void addStudentInfo(Student s) {

        dao.addStudentInfo(s);
    }
}