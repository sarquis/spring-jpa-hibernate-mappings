package com.sqs.spring.dao;

import com.sqs.spring.entity.Instructor;
import com.sqs.spring.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);
}
