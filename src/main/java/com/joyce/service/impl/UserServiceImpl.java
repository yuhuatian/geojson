package com.joyce.service.impl;

import com.joyce.entity.UserInfo;
import com.joyce.repostiory.UserRepository;
import com.joyce.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserInfo save(UserInfo userInfo) {
        return repository.save(userInfo);
    }

    @Override
    public UserInfo selectUserByNameAndPassword(String userName, String password) {
        return repository.findByUserNameAndUserPassword(userName,password);
    }

    @Override
    public void delete(String userId) {
        repository.delete(userId);
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        UserInfo user=new UserInfo();

        user=UserInfo.builder().userType(userInfo.getUserType())
                .userId(userInfo.getUserId())
                .userPassword(userInfo.getUserPassword())
                .userName(userInfo.getUserName())
                .userClass(userInfo.getUserClass())
                .updateTime(new Date()).build();

        return repository.save(user);
    }

    @Override
    public UserInfo findOne(String userId) {
        return repository.findOne(userId);
    }

    @Override
    public Page<UserInfo> findAll(Pageable pageable) {
        return repository.findAll( pageable);
    }
}
