package com.sqs.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sqs.spring.dao.AppDAO;
import com.sqs.spring.entity.Instructor;
import com.sqs.spring.entity.InstructorDetail;

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
	    // createInstructor(appDAO);
	    // findInstructor(appDAO);
	    // deleteInstructor(appDAO);
	    findInstructorDetail(appDAO);
	};

    }

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
