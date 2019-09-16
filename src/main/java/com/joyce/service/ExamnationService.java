package com.joyce.service;

import com.joyce.entity.Examnation;
import com.joyce.entity.SubjectInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ExamnationService {


    //删除试卷
    public Examnation delete(String examnationId);

    //新增试卷
    public Examnation save(Examnation examnation, HttpServletRequest request);

    //修改试卷
    public Examnation update(Examnation examnation,HttpServletRequest request);

    //查询所有试卷
    public Page<Examnation> findAll(Pageable pageable);

    //根据操作人查询所有试卷
    public Page<Examnation> findAllByOperatorId(String operatorId,Pageable pageable);

    //查看试卷详情
    public List<SubjectInfo> findAllSubjectByExamationId(String examationId);
}
