package dv.kinash.hw11;

import dv.kinash.hw11.model.entity.Course;
import dv.kinash.hw11.model.entity.Student;
import dv.kinash.hw11.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class Hw11Application implements CommandLineRunner {
	@Autowired
	private StudentRepository repository;
	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(Hw11Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Student student1 = new Student();
		student1.setName("Adam");
		student1.setCourses(List.of(
				new Course("Mathematics", student1),
				new Course("Physics", student1),
				new Course("Chemistry", student1)));
		Student student2 = new Student();
		student2.setName("Eva");
		student2.setCourses(List.of(
				new Course("Literature", student2),
				new Course("Philosophy", student2),
				new Course("Law", student2)));
		repository.saveAll(List.of(student1, student2));
	}
}
