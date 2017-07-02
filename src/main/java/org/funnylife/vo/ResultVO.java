package org.funnylife.vo;

/**
 * Created by cheng on 2017/7/2.
 */
public class ResultVO<T> {
    private boolean success;
    private T data;
    private String result;

    public void success(){
        this.success = true;
    }

    public void fail(){
        this.success = false;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
