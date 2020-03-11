package edu.nf.student.entity;

/**
 * @author admin
 * @date 2019/11/20
 * 职位表
 */
public class Position {
    //序号
    private Integer pid;
    private String stuPosition;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getStuPosition() {
        return stuPosition;
    }

    public void setStuPosition(String stuPosition) {
        this.stuPosition = stuPosition;
    }
}