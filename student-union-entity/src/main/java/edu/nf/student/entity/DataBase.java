package edu.nf.student.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author admin
 * @date 2019/11/20
 * 资料库
 */
public class DataBase {
    //序号
    private String did;
    //操作员
    private String stuOperator;
    //部门
    private String stuDepartment;


    //文件名称
    private String fileName;
    //上传时间
    //出生日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "ZMT+8")
    private Date fileDate;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getStuOperator() {
        return stuOperator;
    }

    public void setStuOperator(String stuOperator) {
        this.stuOperator = stuOperator;
    }
    public String getStuDepartment() {
        return stuDepartment;
    }

    public void setStuDepartment(String stuDepartment) {
        this.stuDepartment = stuDepartment;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Date getFileDate() {
        return fileDate;
    }

    public void setFileDate(Date fileDate) {
        this.fileDate = fileDate;
    }
}