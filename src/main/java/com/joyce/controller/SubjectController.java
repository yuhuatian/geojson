package com.joyce.controller;

import com.alibaba.fastjson.JSON;
import com.joyce.entity.Examnation;
import com.joyce.entity.SubjectInfo;
import com.joyce.enums.ResultVOStatusEnum;
import com.joyce.repostiory.SubjectReponitory;
import com.joyce.service.impl.SubjectServiceImpl;
import com.joyce.util.ResultVoUtil;
import com.joyce.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/subject/get")
public class SubjectController {

    String subjectId = "";
    @Autowired
    private SubjectServiceImpl subjectService;

    @RequestMapping("/add")
    public String returnJsp() {
        return "admin/addsubject";
    }

    @RequestMapping(value = "/addsubject", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultVo addSubject(String jsondata, HttpServletRequest request) {

        List<SubjectInfo> list = new ArrayList<SubjectInfo>();
        list = JSON.parseArray(jsondata, SubjectInfo.class);
        SubjectInfo temp = list.get(0);
        SubjectInfo after = subjectService.save(temp, request);
        if (after != null) {
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(1, "操作失败");

    }

    @RequestMapping("/alterdetail")
    @ResponseBody
    public ResultVo alterDetail(String subjectId, HttpServletRequest request) {
        /*SubjectInfo subject=subjectService.findOne(subjectId);
        request.getSession().setAttribute("subject",subject);*/
        this.subjectId = subjectId;
        return ResultVoUtil.success("../../subject/get/subjectdeatil", 0);
    }

    @RequestMapping("/subjectdeatil")
    public ModelAndView getOne() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("subject", subjectService.findOne(subjectId));
        mv.setViewName("admin/subjectdetail");
        return mv;
    }


    @RequestMapping("/findAllByExamnationId")
    public ModelAndView findAllByExamnationId() {
        return null;
    }

    @RequestMapping(value = "/subjectsjsp")
    public String getjsp(){
        return "admin/subjects";
    }

    @RequestMapping(value = "/subjects")
    //获取所有题
    @ResponseBody
    public ResultVo getSubjects(@RequestParam("limit") int size, @RequestParam("page") int page) {
        PageRequest request = new PageRequest(page - 1, size);
        List<SubjectInfo> list = new ArrayList<SubjectInfo>();
        list=subjectService.findAll(request).getContent();
        int total=list.size();
        if(list.size()>0){
           return ResultVoUtil.success(list,total);
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());
    }

    @RequestMapping("/subject")
    @ResponseBody
    //获取一张试卷的所有题
    public ResultVo getSubject(String examnationId) {

        List<SubjectInfo> list = subjectService.findAllByExamnationId(examnationId);
        return ResultVoUtil.success(list, list.size());
    }


    @RequestMapping("/delete")
    @ResponseBody
    public ResultVo delete(String subjectId) {
        SubjectInfo subjectInfo = subjectService.delete(subjectId);
        if (subjectInfo == null) {
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(1, "操作失败");
    }

    @RequestMapping(value = "/alt", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultVo alt(String jsondata, HttpServletRequest request) {

        List<SubjectInfo> list = new ArrayList<SubjectInfo>();
        list = JSON.parseArray(jsondata, SubjectInfo.class);
        SubjectInfo temp = list.get(0);

        if (temp.getSubjectId() != null &&
                temp.getSubjectAnswert() != null && temp.getSubjectName() != null &&
                temp.getSubjectAnswera() != null && temp.getSubjectAnswerb() != null &&
                temp.getSubjectAnswerc() != null && temp.getSubjectAnswerd() != null
                ) {
            SubjectInfo before = subjectService.findOne(temp.getSubjectId());
            String b=before.getUpdateTime().toString();
            SubjectInfo after = subjectService.alter(temp, request);
            String a=after.getUpdateTime().toString();
            if (!a.equals(b)) {
                return ResultVoUtil.success();
            }
            return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(), "没有权限");
        }

        return ResultVoUtil.error(1,"操作失败");

    }
}
