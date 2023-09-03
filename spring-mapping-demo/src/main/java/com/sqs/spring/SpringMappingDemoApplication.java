package com.sqs.spring;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sqs.spring.dao.AppDAO;
import com.sqs.spring.entity.Course;
import com.sqs.spring.entity.Instructor;
import com.sqs.spring.entity.InstructorDetail;
import com.sqs.spring.entity.Review;
import com.sqs.spring.entity.Student;

@SpringBootApplication
public class SpringMappingDemoApplication {

    public static void main(String[] args) {
	SpringApplication.run(SpringMappingDemoApplication.class, args);
    }

    // @Bean no need Autowired for AppDAO.
    // CommandLineRunner: Executed after the Spring Beans have been loaded.
    @Bean
    CommandLineRunner commandLineRunner(AppDAO appDAO) {

	return runner -> {
	    // createCourseAndStudents(appDAO);
	    // findCourseAndStudents(appDAO);
	    // findStudentAndCourses(appDAO);
	    addMoreCoursesForStudent(appDAO);
	};

    }

    private void addMoreCoursesForStudent(AppDAO appDAO) {
	int theId = 2;
	Student student = appDAO.findStudentAndCoursesByStudentId(theId);
	Course course1 = new Course("How to dunk - Shaq style");
	Course course2 = new Course("Atari 2600 - Game Development");
	student.addCourse(course1);
	student.addCourse(course2);
	appDAO.update(student);
    }

    @SuppressWarnings("unused")
    private void findStudentAndCourses(AppDAO appDAO) {
	int theId = 2;
	Student tempStudent = appDAO.findStudentAndCoursesByStudentId(theId);
	System.out.println(tempStudent);
	System.out.println(tempStudent.getCourses());
    }

    @SuppressWarnings("unused")
    private void findCourseAndStudents(AppDAO appDAO) {
	int theId = 11;
	Course tempCourse = appDAO.findCourseAndStudentsByCourseId(theId);
	System.out.println(tempCourse);
	System.out.println(tempCourse.getStudents());

    }

    @SuppressWarnings("unused")
    private void createCourseAndStudents(AppDAO appDAO) {
	Course course = new Course("Make a shot - Nothing but net.");
	Student student1 = new Student("John", "Doe", "john@email.com");
	Student student2 = new Student("Mary", "Anne", "mary@email.com");
	course.addStudent(student1);
	course.addStudent(student2);
	appDAO.save(course);
    }

    @SuppressWarnings("unused")
    private void deleteCourseAndReviews(AppDAO appDAO) {
	int theId = 10;
	appDAO.deleteCourseById(theId);
    }

    @SuppressWarnings("unused")
    private void retrieveCourseAndReviews(AppDAO appDAO) {
	int theId = 10;
	Course tempCourse = appDAO.findCourseAndReviewsByCourseId(theId);
	System.out.println(tempCourse);
	System.out.println(tempCourse.getReviews());
    }

    @SuppressWarnings("unused")
    private void createCourseAndReviews(AppDAO appDAO) {
	Course course = new Course("Speedrun Battletoads - tips and tricks.");
	course.addReview(new Review("Great course!"));
	course.addReview(new Review("Cool course!"));
	course.addReview(new Review("Job well done!"));
	appDAO.save(course);
	System.out.println(course);
	System.out.println(course.getReviews());
    }

    @SuppressWarnings("unused")
    private void deleteCourseById(AppDAO appDAO) {
	int theId = 10;
	appDAO.deleteCourseById(theId);
    }

    @SuppressWarnings("unused")
    private void updateCourse(AppDAO appDAO) {
	int theId = 10;
	Course tempCourse = appDAO.findCourseById(theId); // to fill all fields.
	tempCourse.setTitle("Coding for life...");
	appDAO.update(tempCourse);
    }

    @SuppressWarnings("unused")
    private void updateInstructor(AppDAO appDAO) {
	int theId = 1;
	Instructor tempInstructor = appDAO.findInstructorById(theId);
	tempInstructor.setLastName("TESTER");
	appDAO.update(tempInstructor);
    }

    @SuppressWarnings("unused")
    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
	int theId = 100;
	Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);
	System.out.println(tempInstructor);
	System.out.println(tempInstructor.getCourses());
    }

    @SuppressWarnings("unused")
    private void findCoursesForInstructor(AppDAO appDAO) {
	int theId = 1;
	Instructor tempInstructor = appDAO.findInstructorById(theId);
	System.out.println(tempInstructor);
	List<Course> courses = appDAO.findCoursesByInstructorId(theId);
	tempInstructor.setCourses(courses);
	System.out.println(tempInstructor.getCourses());
    }

    @SuppressWarnings("unused")
    private void findInstructorWithCourses(AppDAO appDAO) {
	int theId = 1;
	Instructor tempInstructor = appDAO.findInstructorById(theId);
	System.out.println(tempInstructor);
	System.out.println(tempInstructor.getCourses());
    }

    @SuppressWarnings("unused")
    private void createInstructorWithCourses(AppDAO appDAO) {
	Instructor tempInstructor = new Instructor("Susan", "Public", "susan@email.com");
	InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.susan.com/youtube", "gamer!!!");
	tempInstructor.setInstructorDetail(tempInstructorDetail);
	Course tempCourse1 = new Course("Air Guitar = The Ultimate Guide");
	Course tempCourse2 = new Course("The Pinball Masterclass");
	tempInstructor.add(tempCourse1);
	tempInstructor.add(tempCourse2);
	appDAO.save(tempInstructor);
    }

    @SuppressWarnings("unused")
    private void deleteInstructorDetail(AppDAO appDAO) {
	int theId = 3;
	appDAO.deleteInstructorDetailById(theId);
    }

    @SuppressWarnings("unused")
    private void findInstructorDetail(AppDAO appDAO) {
	int theId = 2;
	InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
	System.out.println(tempInstructorDetail);
	System.out.println(tempInstructorDetail.getInstructor());
    }

    @SuppressWarnings("unused")
    private void deleteInstructor(AppDAO appDAO) {
	int theId = 1;
	appDAO.deleteInstructorById(theId);
    }

    @SuppressWarnings("unused")
    private void findInstructor(AppDAO appDAO) {
	int theId = 2;
	Instructor tempInstructor = appDAO.findInstructorById(theId);
	System.out.println(tempInstructor);
	System.out.println(tempInstructor.getInstructorDetail());
    }

    @SuppressWarnings("unused")
    private void createInstructor(AppDAO appDAO) {
//	Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@email.com");
//	InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.chad.com/youtube", "code!!!");
	Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@email.com");
	InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.chad.com/youtube", "guitar!!!");
	tempInstructor.setInstructorDetail(tempInstructorDetail);
	appDAO.save(tempInstructor);
    }

}
