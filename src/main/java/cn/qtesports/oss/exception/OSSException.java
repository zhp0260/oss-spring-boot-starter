package cn.qtesports.oss.exception;

/**
 * @author Mark
 * @create 2018-09-17 12:25
 * @desc OSS异常
 **/
public class OSSException extends RuntimeException{
    private String msg;
    private int code = 500;

    public OSSException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public OSSException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public OSSException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public OSSException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

}
