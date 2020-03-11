package edu.nf.student.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * @author admin
 * @date 2019/11/19
 * 学生信息表
 */
public class Student {
    //ID
    private Integer sid;
    //姓名
    private String stuName;
    //性别
    private String stuSex;
    //年龄
    private String stuAge;
    //照片
    private String stuImage;

    //身份证
    private String stuCard;
    //电话
    private String stuTel;
    //密码
    private String passWord;
    //出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "ZMT+8")
    private Date stuBirthday;
    //民族
    private String nation;
    //籍贯
    private String nationPlace;
    //政治面貌
    private String politicid;
    //邮箱
    private String stuEmail;
    //QQ
    private String qq;
    //班级
    private String stuClass;
    //职位
    private String stuPosition;
    //所属部门
    private Integer stuDepartment;


    //加入时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "ZMT+8")
    private Date stuAddTime;

    //部门表
    private Department department;
    //权限表
    private Permission permission;
    //职位表
    private Position position;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public String getStuAge() {
        return stuAge;
    }

    public void setStuAge(String stuAge) {
        this.stuAge = stuAge;
    }

    public String getStuImage() {
        return stuImage;
    }

    public void setStuImage(String stuImage) {
        this.stuImage = stuImage;
    }

    public String getStuCard() {
        return stuCard;
    }

    public void setStuCard(String stuCard) {
        this.stuCard = stuCard;
    }

    public String getStuTel() {
        return stuTel;
    }

    public void setStuTel(String stuTel) {
        this.stuTel = stuTel;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Date getStuBirthday() {
        return stuBirthday;
    }

    public void setStuBirthday(Date stuBirthday) {
        this.stuBirthday = stuBirthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNationPlace() {
        return nationPlace;
    }

    public void setNationPlace(String nationPlace) {
        this.nationPlace = nationPlace;
    }

    public String getPoliticid() {
        return politicid;
    }

    public void setPoliticid(String politicid) {
        this.politicid = politicid;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

    public String getStuPosition() {
        return stuPosition;
    }

    public void setStuPosition(String stuPosition) {
        this.stuPosition = stuPosition;
    }

    public Integer getStuDepartment() {
        return stuDepartment;
    }

    public void setStuDepartment(Integer stuDepartment) {
        this.stuDepartment = stuDepartment;
    }

    public Date getStuAddTime() {
        return stuAddTime;
    }

    public void setStuAddTime(Date stuAddTime) {
        this.stuAddTime = stuAddTime;
    }
}