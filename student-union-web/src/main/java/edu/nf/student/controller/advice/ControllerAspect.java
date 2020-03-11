package edu.nf.student.controller.advice;


import edu.nf.student.controller.vo.ResponseVO;
import edu.nf.student.service.exception.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author admin
 * @date 2019/11/14
 */
@ControllerAdvice("edu.nf.student.controller")
public class ControllerAspect {
    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ResponseVO handlerDataAccessException(DataAccessException e){
        ResponseVO vo = new ResponseVO();
        vo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        vo.setMessage(e.getMessage());
        return vo;
    }


}