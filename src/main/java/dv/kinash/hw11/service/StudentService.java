package dv.kinash.hw11.service;

import dv.kinash.hw11.exceptions.CourseNotFoundException;
import dv.kinash.hw11.exceptions.StudentNotFoundExceptions;
import dv.kinash.hw11.model.dto.CourseDTO;
import dv.kinash.hw11.model.dto.StudentDTO;
import dv.kinash.hw11.model.entity.Course;
import dv.kinash.hw11.model.entity.Student;
import dv.kinash.hw11.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {
    final private ModelMapper mapper;
    final private StudentRepository repository;

    public List<StudentDTO> getAllStudent(){
        List<StudentDTO> students = new ArrayList<>();
        repository.findAll().forEach(student -> students.add(mapper.map(student, StudentDTO.class)));
        return students;
    }

    public List<CourseDTO> getStudentCourses(Long studentId){
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundExceptions::new);
        return student.getCourses().stream()
                .map(course -> mapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }

    public void addStudentCourse(Long studentId, String courseName){
        Student student = repository.findById(studentId).orElseThrow(StudentNotFoundExceptions::new);
        List<Course> studentCourses = student.getCourses();
        CourseDTO courseDTO = new CourseDTO(courseName);
        Course course = mapper.map(courseDTO, Course.class);
        course.setStudent(student);
        studentCourses.add(course);
        repository.save(student);
    }

    public void deleteStudentCourse(Long studentId, Long courseId){
        var student = repository.findById(studentId).orElseThrow(StudentNotFoundExceptions::new);
        var studentCourses = student.getCourses();
        var deletedCourse = studentCourses.stream()
                .filter(course -> course.getId().equals(courseId))
                .findFirst()
                .orElseThrow(CourseNotFoundException::new);
        studentCourses.remove(deletedCourse);
        repository.save(student);
    }
}
