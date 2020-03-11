package edu.nf.student.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import edu.nf.student.controller.util.MyFileUtils;
import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.entity.Journal;
import edu.nf.student.entity.Student;
import edu.nf.student.service.JournalService;
import edu.nf.student.service.StudentService;


import edu.nf.student.service.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

/**
 * @author admin
 * @date 2019/11/19
 */
@RestController
public class StudentUnionController extends BaceControler{
    private final static Logger logger = LoggerFactory.getLogger(StudentUnionController.class);

    @Autowired
    private StudentService service;

    @Autowired
    private JournalService journalService;


    /**
     * 获取学生信息
     * @return
     */
    @GetMapping("/student_info")
    public ResponseVO<PageInfo<Student>> studentInfo(Integer pageNum,Integer pageSize){
        PageInfo<Student> pageInfo = service.listStudentInfo(pageNum,pageSize);
         return success(pageInfo);
    }


    /**
     * 获取个人信息
     * @param session
     * @return
     */
    @GetMapping("/get_student_info")
    public ResponseVO getStudentInfo(HttpSession session){
         Student s = (Student)session.getAttribute("user");

        return success(s);
    }


    /**
     * 根据Id查询
     * @param sid
     * @return
     */
    @GetMapping("/get_student_id")
    public ResponseVO getStudentId(String sid){
        Student stu = service.getStudnetInfo(sid);
        return success(stu);
    }


    /**
     * 登录
     * @param stuTel
     * @param passWord
     * @param session
     * @return
     */
    @GetMapping("/login")
    public ResponseVO login(String stuTel,String passWord, HttpSession session){
        Student s = service.studentLogin(stuTel,passWord);
        session.setAttribute("user",s);
        logger.info(s.getDepartment().getDepartment()+s.getStuName()+"  时间:"+new Date());
        Journal journal = new Journal();
        journal.setStuOperator(s.getStuName());
        journal.setStuOperation("上线");
        journal.setOperatorDate(new Date());
        journalService.addJournal(journal);
        return success("登录成功");

    }


    /**
     * 模糊查询
     * @param findValue
     * @return
     */
    @GetMapping("/get_student_find")
    public ResponseVO getStudentFind(String findValue){

        List<Student> stu = service.getStudentFind(findValue);
        return success(stu);
    }


    /**
     * 根据ID删除
     * @param sid
     * @return
     */
    @GetMapping("/delete_student_id")
    public ResponseVO deleteStudentId(Integer sid){
        service.deleteStudentId(sid);
        return success("删除成功");
    }


    /**
     * 录入信息
     * @param jsonStr
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/add_sutdent_info")
    public ResponseVO addStudentInfo( String jsonStr, MultipartFile[] files) throws IOException {
        Student student = JSON.parseObject(jsonStr,Student.class);

        //将文件上传到当前web项目部署的目录中，先获取部署目录
        String uploadPath = "D:"+File.separator + "upload";
        List<String> fileNames = new ArrayList<>();
        //创建上传目录
        MyFileUtils.createUploadDir(uploadPath);
        for (MultipartFile file : files) {
            //获取上传文件的文件名
            String fileName = file.getOriginalFilename();
            fileName = MyFileUtils.newFileName(fileName);
            //构建上传的文件
            File uploadFile = MyFileUtils.createUploadFile(uploadPath, fileName);
            //执行上传
            file.transferTo(uploadFile);
            //保存文件名

            student.setStuAddTime(new Date());
            student.setStuImage(fileName);
            service.addStudentInfo(student);

            fileNames.add(fileName);
        }
            return success("添加成功");

    }


    //用于退出页面
    @GetMapping("/tologout")
    public String tologout(HttpSession session, HttpServletResponse response) throws IOException {
        Student student = (Student)session.getAttribute("user");
        Journal journal = new Journal();
        journal.setStuOperator(student.getStuName());
        journal.setStuOperation("退出");
        journal.setOperatorDate(new Date());
        journalService.addJournal(journal);

        session.setAttribute("user",null);
        response.sendRedirect("../page/login.html");
        return "退出成功";
    }


}