package com.sqs.spring.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sqs.spring.entity.Instructor;
import com.sqs.spring.entity.InstructorDetail;

import jakarta.persistence.EntityManager;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    // @Autowired
    public AppDAOImpl(EntityManager entityManager) {
	this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
	entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
	return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
	Instructor theInstructor = entityManager.find(Instructor.class, theId);
	entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
	return entityManager.find(InstructorDetail.class, theId);
    }

}
