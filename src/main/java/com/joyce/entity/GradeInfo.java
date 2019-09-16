package com.joyce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 成绩
 */

@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class GradeInfo {
    //成绩ID
    @Id
    private String gradeId;
    //学生ID
    private String userId;
    //试卷ID
    private String examnationId;
    //分数
    private int grades=0;

}
