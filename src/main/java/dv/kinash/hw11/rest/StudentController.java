package dv.kinash.hw11.rest;

import dv.kinash.hw11.model.dto.CourseDTO;
import dv.kinash.hw11.model.dto.StudentDTO;
import dv.kinash.hw11.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService service;

    @GetMapping({"", "/", "/list"})
    public List<StudentDTO> getAllStudent(){
        return service.getAllStudent();
    }

    @GetMapping({"/{id}", "/{id}/"})
    public List<CourseDTO> getStudentCourses(@PathVariable Long id){
        return service.getStudentCourses(id);
    }

    @RequestMapping(value = "/{id}/add/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudentCourse(@PathVariable Long id, @PathVariable String name){
        service.addStudentCourse(id, name);
    }

    @RequestMapping(value = "/{id}/delete/{courseId}", method = {RequestMethod.GET, RequestMethod.DELETE})
    public void deleteStudentCourse(@PathVariable Long id, @PathVariable Long courseId){
        service.deleteStudentCourse(id, courseId);
    }
}
