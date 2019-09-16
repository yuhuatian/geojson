package com.joyce.service;

import com.joyce.entity.GradeInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GradeService {

    //新增成绩
    public GradeInfo save(GradeInfo gradeInfo);

    //查询成绩
    public Page<GradeInfo> findAllByUserId(String userId,Pageable pageable);

    //查询成绩
    public Page<GradeInfo> findAll(Pageable pageable);
}
