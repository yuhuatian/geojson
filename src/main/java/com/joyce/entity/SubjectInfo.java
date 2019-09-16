package com.joyce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInfo {
    @Id
    private String subjectId;
    private String subjectName;
    private String subjectStatus;
    private String examnationId;
    private Integer subjectWorth;
    private String subjectAnswera;
    private String subjectAnswerb;
    private String subjectAnswerc;
    private String subjectAnswerd;
    private String subjectAnswert;
    private String operatorId;
    private Date updateTime;
}
