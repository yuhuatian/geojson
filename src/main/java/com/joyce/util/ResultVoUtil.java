package com.joyce.util;

import com.joyce.enums.ResultVOStatusEnum;
import com.joyce.vo.ResultVo;

public class ResultVoUtil {
    public static ResultVo success(Object object,int total){
        ResultVo resultVO=new ResultVo();
        resultVO.setTotal(total);
        resultVO.setCode(ResultVOStatusEnum.SUCCESS.getCode());
        resultVO.setMsg(ResultVOStatusEnum.SUCCESS.getMsg());
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVo success(){
        return success(null,0);
    }
    public static ResultVo error(int code, String msg){
        ResultVo resultVO=new ResultVo();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
