package com.joyce.repostiory;

import com.joyce.entity.Examnation;
import javafx.scene.chart.PieChartBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamnationRepository extends JpaRepository<Examnation,String> {
    public Page<Examnation> findAllByOperatorId(String operatorId,Pageable pageable);
    public Page<Examnation> findAll(Pageable pageable);
    public Examnation findByExamnationName(String s);
}
