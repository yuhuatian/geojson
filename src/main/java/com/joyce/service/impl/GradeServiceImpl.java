package com.joyce.service.impl;

import com.joyce.entity.GradeInfo;
import com.joyce.repostiory.GradeRepository;
import com.joyce.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Override
    public Page<GradeInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Autowired
    private GradeRepository repository;
    @Override
    //新增成绩
    public GradeInfo save(GradeInfo gradeInfo) {
        return repository.save(gradeInfo);
    }

    @Override
    //查询成绩
    public Page<GradeInfo> findAllByUserId(String userId,Pageable pageable) {
        return repository.findAllByUserId(userId,pageable);
    }
}
