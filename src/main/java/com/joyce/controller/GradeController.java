package com.joyce.controller;

import com.alibaba.fastjson.JSON;
import com.joyce.entity.GradeInfo;
import com.joyce.entity.SubjectInfo;
import com.joyce.enums.ResultVOStatusEnum;
import com.joyce.service.impl.GradeServiceImpl;
import com.joyce.service.impl.SubjectServiceImpl;
import com.joyce.util.ResultVoUtil;
import com.joyce.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/grade/get")
public class GradeController {

    @Autowired
    private GradeServiceImpl gradeService;
    @Autowired
    SubjectServiceImpl subjectService;

    @RequestMapping("gradejsp")
    public String getjsp(){
        return "admin/grades";
    }

    @RequestMapping("/grade")
    @ResponseBody
    public ResultVo getGrade(@RequestParam("limit") int size, @RequestParam("page") int page,HttpServletRequest r){
        PageRequest request = new PageRequest(page - 1, size);
        List<GradeInfo> list = new ArrayList<GradeInfo>();
        String userId=(String )r.getSession().getAttribute("userId");
        String type=(String) r.getSession().getAttribute("userType");
        if(type.equals("0")){
            list=gradeService.findAllByUserId(userId,request).getContent();
        }else {
            list=gradeService.findAll(request).getContent();
        }
        if(list.size()>0){
            return ResultVoUtil.success(list,list.size());
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());

    }

    @RequestMapping(value = "/addgrade", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultVo alt(String jsondata, HttpServletRequest request) {
        List<SubjectInfo> list= new ArrayList<SubjectInfo>();
        list= JSON.parseArray(jsondata, SubjectInfo.class);
        SubjectInfo s=list.get(0);
        SubjectInfo temp=subjectService.findOne(s.getSubjectId());
        String exanmeationID=temp.getExamnationId();
        int grade=0;
        if (list.size()>0){

            for (SubjectInfo subjectInfo:list){
                SubjectInfo subjectInfo1=subjectService.findOne(subjectInfo.getSubjectId());

                if (subjectInfo1.getSubjectAnswert().equals(subjectInfo.getSubjectAnswert())){
                    grade=grade+subjectInfo1.getSubjectWorth();
                }
            }
           String userId= (String) request.getSession().getAttribute("userId");
            GradeInfo gradeInfo=GradeInfo.builder().gradeId("5").userId(userId)
                    .examnationId(exanmeationID).grades(grade)
                    .gradeId(Integer.toString(gradeService.findAllByUserId(userId,null).getContent().size()+1))
                    .build();
            GradeInfo gradeInfo1=gradeService.save(gradeInfo);
            System.out.println(gradeInfo1);
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());


    }



}
