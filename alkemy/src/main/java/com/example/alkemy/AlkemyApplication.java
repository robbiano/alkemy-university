package com.example.alkemy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalTime;

@SpringBootApplication
public class AlkemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlkemyApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository, SubscriptionsRepository subscriptionsRepository) {
		return (args) -> {

		// Students
			User student1 = new User(1l,10,28165509l, "student");
			userRepository.save(student1);

			User student2 = new User(2l,11,40163244l, "student");
			userRepository.save(student2);

		// teachers
            Teacher teacher1 = new Teacher(1l, "Emilio", "Rasic", 37276610l, true );
            teacherRepository.save(teacher1);

            Teacher teacher2 = new Teacher(2l, "Carlos", "Rivero", 47276610l, true );
			teacherRepository.save(teacher2);

		// subjects
			Subject subject1 = new Subject( 1l,"Sistemas y Metodos", LocalTime.of(11,00), teacher1 , 10);
			subjectRepository.save(subject1);

			Subject subject2 = new Subject( 2l,"Análisis Matemático", LocalTime.of(12,00), teacher2 , 10);
			subjectRepository.save(subject2);

			//subscriptions
			Subscriptions subscription1 = new Subscriptions(1l, subject1, student1);
			subscriptionsRepository.save(subscription1);

			Subscriptions subscription2 = new Subscriptions(2l, subject2, student2);
			subscriptionsRepository.save(subscription2);

		};

	}

}
