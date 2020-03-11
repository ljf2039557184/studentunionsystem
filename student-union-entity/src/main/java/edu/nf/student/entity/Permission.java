package edu.nf.student.entity;

/**
 * @author admin
 * @date 2019/11/20
 *
 */
public class Permission {
    //序号
    private Integer pid;
    //权限等级 超级管理员、管理员、部员
    private String stuGrade;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(String stuGrade) {
        this.stuGrade = stuGrade;
    }
}