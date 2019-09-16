package com.joyce.service.impl;

import com.joyce.entity.Examnation;
import com.joyce.entity.SubjectInfo;
import com.joyce.repostiory.ExamnationRepository;
import com.joyce.repostiory.SubjectReponitory;
import com.joyce.service.ExamnationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class ExamnationServiceImpl implements ExamnationService {


    @Autowired
    private ExamnationRepository repository;
    @Autowired
    private SubjectReponitory subjectReponitory;

    @Override
    public Examnation delete(String examnationId) {
         repository.delete(examnationId);
         return repository.findOne(examnationId);

    }

    @Override
    //新增试题
    public Examnation save(Examnation examnation, HttpServletRequest request) {
        String  operatorid=(String)request.getSession().getAttribute("userId");
        int size=repository.findAll().size()+2;
        String examnationId=Integer.toString(size);
        examnation.setExamnationId(examnationId);
        examnation.setOperatorId(operatorid);
        repository.save(examnation);
        return repository.findOne(examnationId);
    }

    @Override
    //更新试题
    public Examnation update(Examnation examnation,HttpServletRequest request) {
        return repository.save(examnation);
    }

    @Override
    public Page<Examnation> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Examnation> findAllByOperatorId(String operatorId, Pageable pageable) {
        return repository.findAllByOperatorId(operatorId,pageable);
    }

    @Override
    public List<SubjectInfo> findAllSubjectByExamationId(String examationId) {
        return subjectReponitory.findAllByExamnationId(examationId);
    }
}
