package com.joyce.repostiory;

import com.joyce.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserRepository extends JpaRepository<UserInfo,String> {
    public UserInfo findByUserNameAndUserPassword(String userName,String password);
    public Page<UserInfo> findAll(Pageable pageable);
}
