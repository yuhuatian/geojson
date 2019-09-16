package com.joyce.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


@Data
public class ResultVo<T> implements Serializable{


    private static final long serialVersionUID = 4151455040216454109L;
    //返回的状态码
    private Integer code;
    //提示信息
    private String msg;
    //返回的数据
    private T data;

    private int total;

    public Map<String, Object> toMap() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>();
        result.put("code", code);
        result.put("msg", msg);
        result.put("data", data);

        return result;
    }
}
