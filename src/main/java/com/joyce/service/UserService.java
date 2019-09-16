package com.joyce.service;

import com.joyce.entity.UserInfo;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {

    //新增用户
    public UserInfo save(UserInfo userInfo);
    //查询用户
    public UserInfo selectUserByNameAndPassword(String userName,String password);
    //修改用户
    public UserInfo update(UserInfo userInfo);

    public Page<UserInfo> findAll(Pageable pageable);

    public UserInfo findOne(String userId);

    public void delete(String userId);
}
