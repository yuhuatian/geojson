package com.joyce.controller;


import com.alibaba.fastjson.JSON;
import com.joyce.entity.Examnation;
import com.joyce.entity.SubjectInfo;
import com.joyce.enums.ResultVOStatusEnum;
import com.joyce.service.impl.ExamnationServiceImpl;
import com.joyce.service.impl.SubjectServiceImpl;
import com.joyce.util.ResultVoUtil;
import com.joyce.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/examnation/get")
public class ExamnationController {

    @Autowired
    private ExamnationServiceImpl examnationService;
    @Autowired
    private SubjectServiceImpl subjectService;

    static String examnationId = "";


    @RequestMapping("/examnation")
    @ResponseBody
    public ResultVo getExamnation(@RequestParam("limit") int size, @RequestParam("page") int page,HttpServletRequest r) {
        PageRequest request = new PageRequest(page - 1, size);
        List<Examnation> list = new ArrayList<Examnation>();
        String type=(String )r.getSession().getAttribute("userType");
        if(type.equals("1")){
            list=examnationService.findAllByOperatorId((String )r.getSession().getAttribute("userId"),request).getContent();
        }else {
            list = examnationService.findAll(request).getContent();
        }
        return ResultVoUtil.success(list, list.size());
    }

    @RequestMapping("/add")
    public String returnJsp() {
        return "admin/addexamnation";
    }

    @RequestMapping(value = "/addexamnation", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultVo addexamnation(String jsondata, HttpServletRequest request) {

        List<Examnation> list = new ArrayList<Examnation>();
        list = JSON.parseArray(jsondata, Examnation.class);
        Examnation temp = list.get(0);
        Examnation after = examnationService.save(temp, request);
        if (after!=null) {
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(1, "操作失败");

    }


    @RequestMapping("/examnationdetail")
    @ResponseBody
    public ResultVo getExamnationDetail(String examnationId, HttpServletRequest request)throws ServletException{
        this.examnationId=examnationId;
        String viewName="/examnation/get/deatiljsp";
        return ResultVoUtil.success(viewName,1);

    }



    @RequestMapping("/examnationjsp")
    public String getPage(){
        return "admin/examnation";
    }


    @RequestMapping("/deatiljsp")
    public ModelAndView test(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.addObject("list",subjectService.findAllByExamnationId(examnationId));
        String userType=(String) request.getSession().getAttribute("userType");
        if(userType.equals("0")){
            mv.setViewName("admin/testing");
        }else {
            mv.setViewName("admin/examnationdetail");
        }

        return mv;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo delete(String examnationId){
        Examnation temp=examnationService.delete(examnationId);
        if(temp==null){
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());
    }
}
