package com.springboot.model;

import java.io.Serializable;

/**
* @Name: RequestResult
* @Description:  请求结果
* @param
* @return
* @date 2017/10/18 14:02
*/

public class RequestResult implements Serializable {

    /**
     *  true: 正常操作
     *  false: 条件不符合 或者 异常
     */

    boolean success;

    /**
     *  1:  成功
     *  0:  条件不符合
     * -1： 异常
     */

    int code;

    /**
     * 信息提示
     */

    String tip;

    /**
     * 返回值 默认值 null
     */

    Object data;

    public void setSucceed(String tip,Object data){
        this.success=true;
        this.code=1;
        this.tip=tip;
        this.data=data;
    }

    /**
     * 失败回复
     * @param tip
     */
    public void setFail(String tip){
        this.success=false;
        this.code=0;
        this.tip=tip;
        this.data=null;
    }

    /**
     * 异常回复
     * @param tip
     */
    public void setException(String tip){
        this.success=false;
        this.code=-1;
        this.tip=tip;
        this.data=null;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
