package com.joyce.repostiory;

import com.joyce.entity.Examnation;
import com.joyce.entity.SubjectInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectReponitory extends JpaRepository<SubjectInfo,String> {
    public Page<SubjectInfo> findAllByOperatorId(String operator,Pageable pageable);
    public List<SubjectInfo> findAllByExamnationId(String examnationId);
    public Page<SubjectInfo> findAllByExamnationId(String examnationId,Pageable pageable);
    public Page<SubjectInfo> findAll(Pageable pageable);
}
