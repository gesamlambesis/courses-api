package com.school.courses.controller;




import com.school.courses.dto.CourseSummaryResponse;
import com.school.courses.dto.CreateStudentRequest;
import com.school.courses.dto.StudentResponse;
import com.school.courses.model.Course;
import com.school.courses.model.Student;
import com.school.courses.repository.CourseRepository;
import com.school.courses.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody CreateStudentRequest request) {
        List<Course> courses = courseRepository.findAllById(request.getCourseIds());

        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setCourses(new HashSet<>(courses));

        Student savedStudent = studentRepository.save(student);

        // Map courses to CourseSummaryResponse
        List<CourseSummaryResponse> courseSummaries = savedStudent.getCourses()
                .stream()
                .map(course -> new CourseSummaryResponse(course.getCode(), course.getTitle()))
                .toList();

        return new StudentResponse(savedStudent.getFirstName(), savedStudent.getLastName(), courseSummaries);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(student -> {
                    // Map courses to CourseSummaryResponse
                    List<CourseSummaryResponse> courseSummaries = student.getCourses()
                            .stream()
                            .map(course -> new CourseSummaryResponse(course.getCode(), course.getTitle()))
                            .toList();

                    return new StudentResponse(student.getFirstName(), student.getLastName(), courseSummaries);
                })
                .toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());

            Student savedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok(savedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

