package com.sqs.spring.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sqs.spring.entity.Course;
import com.sqs.spring.entity.Instructor;
import com.sqs.spring.entity.InstructorDetail;
import com.sqs.spring.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AppDAOImpl implements AppDAO {

    private EntityManager entityManager;

    // @Autowired (no need)
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
	TypedQuery<Course> query = entityManager.createQuery("FROM Course WHERE instructor.id = :data", Course.class);
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

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
	Course tempCourse = entityManager.find(Course.class, theId);
	entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
	entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
	String sql = " SELECT c FROM Course c 		";
	sql += "     LEFT JOIN FETCH c.reviews 		";
	sql += "               WHERE c.id = :data 	";
	TypedQuery<Course> query = entityManager.createQuery(sql, Course.class);
	query.setParameter("data", theId);
	Course course = query.getSingleResult();
	return course;
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
	String sql = " SELECT c FROM Course c 		";
	sql += "     LEFT JOIN FETCH c.students 	";
	sql += "               WHERE c.id = :data 	";
	TypedQuery<Course> query = entityManager.createQuery(sql, Course.class);
	query.setParameter("data", theId);
	Course course = query.getSingleResult();
	return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
	String sql = " SELECT s FROM Student s 		";
	sql += "     LEFT JOIN FETCH s.courses 		";
	sql += "               WHERE s.id = :data 	";
	TypedQuery<Student> query = entityManager.createQuery(sql, Student.class);
	query.setParameter("data", theId);
	Student student = query.getSingleResult();
	return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
	entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
	Student student = entityManager.find(Student.class, theId);
	entityManager.remove(student);
    }

}
