package com.joyce.repostiory;

import com.joyce.entity.GradeInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GradeRepositoryTest {

    @Autowired
    private GradeRepository repository;
    @Test
    public void save(){
//        GradeInfo grade= new GradeInfo();
//        grade.setExamnationId("test");
//        grade.setGradeId("test");
//        grade.setGrades(100);
//        grade.setUserId("test");
//        GradeInfo grade2= repository.save(grade);

    }
}