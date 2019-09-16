package com.joyce.service;


import com.joyce.entity.SubjectInfo;
import com.joyce.repostiory.SubjectReponitory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SubjectService {

    public SubjectInfo save(SubjectInfo subjectInfo, HttpServletRequest request);

    //新增试题
    public SubjectInfo alter(SubjectInfo subjectInfo, HttpServletRequest request);

    //更新试题
    public SubjectInfo update(SubjectInfo subjectInfo,HttpServletRequest request);

    //查询所有试题
    public Page<SubjectInfo> findAllSubjects(Pageable pageable);

    //删除试题
    public SubjectInfo delete(String subjeceId);

    //根据ID查询
    public SubjectInfo findOne(String id);

    //查询所有题
    public Page<SubjectInfo> findAll(Pageable pageable);

    //根据操作人员查询试题
    public Page<SubjectInfo> findAllByOperator(String operator, Pageable pageable);

    //根据试卷类型查询试题
    public Page<SubjectInfo> findAllByExamnationType(String type,Pageable pageable);

    //根据试卷ID查询
    public List<SubjectInfo> findAllByExamnationId(String examnationId);

    public Page<SubjectInfo> findAllByExamnationId(String examnationId,Pageable pageable);


}
