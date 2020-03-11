package edu.nf.student.entity;

/**
 * @author admin
 * @date 2019/11/20
 * 部门表
 */
public class Department {
    //序号
    private Integer did;
    //部门名称
    private String department;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
