package com.joyce.vo;

import com.joyce.entity.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class UserVo {

    List<UserInfo> userInfoList= new ArrayList<UserInfo>();

    public List<UserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }


}
