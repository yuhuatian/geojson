package com.joyce.service.impl;

import com.joyce.entity.SubjectInfo;
import com.joyce.repostiory.ExamnationRepository;
import com.joyce.repostiory.SubjectReponitory;
import com.joyce.service.SubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SubjectServiceImpl  implements SubjectService{

    @Autowired
    private SubjectReponitory reponitory;
    @Autowired
    private ExamnationRepository examnationRepository;

    @Override
    public SubjectInfo save(SubjectInfo subjectInfo, HttpServletRequest request) {
        subjectInfo.setOperatorId((String)request.getSession().getAttribute("userId"));
        subjectInfo.setExamnationId(examnationRepository.findByExamnationName(subjectInfo.getExamnationId()).getExamnationId());
        subjectInfo.setSubjectId(Integer.toString(reponitory.findAll().size()+3));
        reponitory.save(subjectInfo);
        return reponitory.findOne(subjectInfo.getSubjectId());
    }

    @Override
    public SubjectInfo alter(SubjectInfo subjectInfo, HttpServletRequest request) {
        SubjectInfo temp=new SubjectInfo();
        BeanUtils.copyProperties(reponitory.findOne(subjectInfo.getSubjectId()),temp);
        subjectInfo=SubjectInfo.builder()
                .subjectName(subjectInfo.getSubjectName())
                .subjectAnswera(subjectInfo.getSubjectAnswera())
                .subjectAnswerb(subjectInfo.getSubjectAnswerb())
                .subjectAnswerc(subjectInfo.getSubjectAnswerc())
                .subjectAnswerd(subjectInfo.getSubjectAnswerd())
                .subjectAnswert(subjectInfo.getSubjectAnswert())
                .subjectStatus(temp.getSubjectStatus())
                .subjectWorth(temp.getSubjectWorth())
                .examnationId(temp.getExamnationId())
                .subjectId(subjectInfo.getSubjectId())
                .updateTime(new Date()).build();
        String operator=(String)request.getSession().getAttribute("userId");
        subjectInfo.setOperatorId(operator);
        return reponitory.save(subjectInfo);
    }

    @Override
    public Page<SubjectInfo> findAll(Pageable pageable) {
        return reponitory.findAll(pageable);
    }

    @Override
    public SubjectInfo delete(String subjeceId) {
         reponitory.delete(subjeceId);
         return reponitory.findOne(subjeceId);
    }

    @Override
    public Page<SubjectInfo> findAllByExamnationId(String examnationId, Pageable pageable) {
        return reponitory.findAllByExamnationId(examnationId,pageable);
    }

    @Override
    public List<SubjectInfo> findAllByExamnationId(String examnationId) {
        return reponitory.findAllByExamnationId(examnationId);
    }

    @Override
    public SubjectInfo update(SubjectInfo subjectInfo, HttpServletRequest request) {
        String operator=(String )request.getSession().getAttribute("userId");
        subjectInfo.setOperatorId(operator);
        return reponitory.save(subjectInfo);
    }

    @Override
    public Page<SubjectInfo> findAllSubjects(Pageable pageable) {
        return reponitory.findAll(pageable);
    }

    @Override
    public SubjectInfo findOne(String id) {
        return reponitory.findOne(id);
    }

    @Override
    public Page<SubjectInfo> findAllByOperator(String operator, Pageable pageable) {
        return reponitory.findAllByOperatorId(operator,pageable);
    }

    @Override
    public Page<SubjectInfo> findAllByExamnationType(String type, Pageable pageable) {
        return null;
    }
}
