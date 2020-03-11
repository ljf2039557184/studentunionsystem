package edu.nf.student.service.exception;

/**
 * @author admin
 * @date 2019/11/14
 * 自定义访问异常
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }

    public DataAccessException(String message) {
        super(message);
    }
}