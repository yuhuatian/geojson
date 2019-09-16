package com.joyce.repostiory;

import com.joyce.entity.GradeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GradeRepository extends JpaRepository<GradeInfo,String> {

    public Page<GradeInfo> findAllByUserId(String userId,Pageable pageable);
    public Page<GradeInfo> findAll(Pageable pageable);
}
