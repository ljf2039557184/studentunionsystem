package edu.nf.student.controller;


import edu.nf.student.controller.vo.ResponseVO;
import org.springframework.http.HttpStatus;

/**
 * @author admin
 * @date 2019/11/14
 */
public class BaceControler {
    public <T> ResponseVO<T> success(T data){
        ResponseVO<T> vo = new ResponseVO<>();
        vo.setCode(HttpStatus.OK.value());
        vo.setData(data);
        return vo;
    }

    public ResponseVO success(String message){
        ResponseVO vo = new ResponseVO<>();
        vo.setCode(200);
        vo.setMessage(message);
        return vo;
    }

    public ResponseVO fail(Integer code){
        ResponseVO vo = new ResponseVO<>();
        vo.setCode(code);
        return vo;
    }

    public ResponseVO fail(Integer code,String message){
        ResponseVO vo = new ResponseVO<>();
        vo.setCode(code);
        vo.setMessage(message);
        return vo;
    }
}