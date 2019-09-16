package com.joyce.controller;

import com.alibaba.fastjson.JSON;
import com.joyce.entity.UserInfo;
import com.joyce.enums.ResultVOStatusEnum;
import com.joyce.service.impl.UserServiceImpl;

import com.joyce.util.ResultVoUtil;
import com.joyce.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller

public class UserController {

    @Autowired
    private UserServiceImpl userService;
    private static String userid;

    @RequestMapping("/login")
    public String getLoginjsp(){
        return "admin/login";
    }


    @RequestMapping("/getlogin")
    public String  login(String userName,String password,HttpServletRequest request){
        UserInfo user=userService.selectUserByNameAndPassword(userName,password);
        if (user!=null){
            request.getSession().setAttribute("userId",user.getUserId());
            request.getSession().setAttribute("userType",user.getUserType());
            return "admin/main";
        }
        else{
            return "admin/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "admin/login";
    }

    @RequestMapping("/register")
    public String getRegisterjsp(){
        return "admin/register";
    }

    @RequestMapping("/getregister")
    public String register(UserInfo userInfo,HttpServletRequest request){
        UserInfo user=UserInfo.builder().userClass(userInfo.getUserClass())
                .userName(userInfo.getUserName())
                .userPassword(userInfo.getUserPassword())
                .userType(userInfo.getUserType()).userId(userInfo.getUserName()).build();

        UserInfo temp1=userService.selectUserByNameAndPassword(userInfo.getUserName(),userInfo.getUserPassword());
        if(temp1==null){
            userService.save(user);
            UserInfo temp= userService.selectUserByNameAndPassword(userInfo.getUserName(),userInfo.getUserPassword());
            if (temp!=null){
                return "admin/login";
            }
        }
        return "admin/register";
    }

    @RequestMapping(value="/deleteuser")
    @ResponseBody
    public ResultVo delateUser(String userId){
        userService.delete(userId);
        if(userService.findOne(userId)==null){
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());
    }


    @RequestMapping("/getalluser")
    @ResponseBody
    public ResultVo getAllUser(@RequestParam("limit") int size, @RequestParam("page") int page, HttpServletRequest r){
        PageRequest request = new PageRequest(page - 1, size);
        List<UserInfo> userInfoList=userService.findAll(request).getContent();
        size=userInfoList.size();
        if(size>0){
            return ResultVoUtil.success(userInfoList,size);
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());

    }

    @RequestMapping("/getuserjsp")
    public String getjsp(){
        return "admin/userList";
    }

    @RequestMapping("/queryuserdetail")
    @ResponseBody
    public ResultVo getuserdeatisjap(String userId){
        userid=userId;
        return ResultVoUtil.success("queryuser",0);
    }

    @RequestMapping("/queryuser")
    public ModelAndView getmv(){
        UserInfo user =userService.findOne(userid);
        ModelAndView mv= new ModelAndView();
        mv.addObject("user",user);
        mv.setViewName("admin/user");
        return mv;
    }

    @RequestMapping(value = "/alter", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResultVo alt(String jsondata, HttpServletRequest request) {

        List<UserInfo> list = new ArrayList<UserInfo>();
        list = JSON.parseArray(jsondata, UserInfo.class);
        UserInfo temp = list.get(0);

        UserInfo before = userService.findOne(temp.getUserId());
        String b=before.getUpdateTime().toString();
        UserInfo after = userService.update(temp);
        String a=after.getUpdateTime().toString();
        if(!a.equals(b)){
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error(ResultVOStatusEnum.ERROR.getCode(),ResultVOStatusEnum.ERROR.getMsg());


    }


    @RequestMapping("vue")
    @ResponseBody
    public ResultVo getVueData(String page, String pageSize){

         PageRequest request = new PageRequest(Integer.valueOf(page )- 1, Integer.valueOf(pageSize));
        List<UserInfo> userList=new ArrayList<>();
        Page<UserInfo> page1=userService.findAll(request);
        return ResultVoUtil.success(page1.getContent(),page1.getTotalPages());


    }

}
