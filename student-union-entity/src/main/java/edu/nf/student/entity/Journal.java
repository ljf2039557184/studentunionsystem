package edu.nf.student.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author admin
 * @date 2019/11/20
 * 日志表
 */
public class Journal {
    private Integer jid;
    //操作人员
    private String stuOperator;
    //操作内容
    private String stuOperation;
    //操作时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "ZMT+8")
    private Date operatorDate;

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getStuOperator() {
        return stuOperator;
    }

    public void setStuOperator(String stuOperator) {
        this.stuOperator = stuOperator;
    }

    public String getStuOperation() {
        return stuOperation;
    }

    public void setStuOperation(String stuOperation) {
        this.stuOperation = stuOperation;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }
}