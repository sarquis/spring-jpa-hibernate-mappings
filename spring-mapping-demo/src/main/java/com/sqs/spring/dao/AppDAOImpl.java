package com.sqs.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sqs.spring.entity.Course;
import com.sqs.spring.entity.Instructor;
import com.sqs.spring.entity.InstructorDetail;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

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
	List<Course> courses = theInstructor.getCourses();
	courses.forEach(c -> c.setInstructor(null)); // deleting FKs (to avoid constraint violation)
	entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
	return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
	InstructorDetail theInstructorDetail = entityManager.find(InstructorDetail.class, theId);
	theInstructorDetail.getInstructor().setInstructorDetail(null);
	entityManager.remove(theInstructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
	TypedQuery<Course> query = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
	query.setParameter("data", theId);
	List<Course> courses = query.getResultList();
	return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
	String sql = " SELECT i FROM Instructor i 	";
	sql += "     LEFT JOIN FETCH i.courses 		";
	sql += "     LEFT JOIN FETCH i.instructorDetail	";
	sql += "               WHERE i.id = :data 	";
	TypedQuery<Instructor> query = entityManager.createQuery(sql, Instructor.class);
	query.setParameter("data", theId);
	Instructor instructor = query.getSingleResult();
	return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
	entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
	entityManager.merge(tempCourse);
    }

    @Override
    public Course findCourseById(int theId) {
	return entityManager.find(Course.class, theId);
    }

}
