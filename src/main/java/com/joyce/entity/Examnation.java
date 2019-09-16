package com.joyce.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
public class Examnation {
    @Id
    private String examnationId;
    private String examnationName;
    private String examnationType;
    private String examnationStatus;
    private String operatorId;

}
