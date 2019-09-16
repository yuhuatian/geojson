package com.joyce.controller;



import com.joyce.entity.SubjectInfo;
import com.joyce.entity.UserInfo;
import com.joyce.repostiory.UserRepository;
import com.joyce.service.impl.SubjectServiceImpl;
import com.joyce.util.ResultVoUtil;
import com.joyce.vo.ResultVo;
import com.joyce.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/get")
public class TestController {

    @Autowired
    private SubjectServiceImpl repository;
    @RequestMapping("/testdata")
    @ResponseBody
    public ResultVo getTestData(@RequestParam("limit") int size, @RequestParam("page")int page, @RequestParam("start")int start ){
        UserVo userVo=new UserVo();
        PageRequest pageRequest=new PageRequest(page-1,size);
        Page<SubjectInfo> page1=repository.findAllSubjects(pageRequest);
        System.out.println(page1.getSize());
        return ResultVoUtil.success(page1.getContent(),page1.getSize());
    }

    @RequestMapping("/testjsp")
    public String testjsp(){
        return "admin/test";
    }
    @RequestMapping("/getjsp2")
    public String testjsp2(){
        return "admin/index";
    }


    @RequestMapping("/delete")
    @ResponseBody
    public boolean deleteTest(String id){

       /* repository.delete(id);
        UserInfo userInfo=repository.findOne(id);
        if(userInfo==null){
            return true;
        }
        else{
            return false;
        }*/
       return true;
    }
}
