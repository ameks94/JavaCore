package com.labs.content;

/**
 * Created by ameks on 10.09.2016.
 */
public class Content {

    private String msg = "Hello word !!!!";


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Content{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
