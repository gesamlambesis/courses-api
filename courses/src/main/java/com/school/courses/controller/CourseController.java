package com.school.courses.controller;

import com.school.courses.dto.CourseResponse;
import com.school.courses.dto.StudentSummaryResponse;
import com.school.courses.model.Course;
import com.school.courses.model.Student;
import com.school.courses.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> {
                    List<StudentSummaryResponse> studentList = course.getStudents().stream()
                            .map(student -> new StudentSummaryResponse(student.getFirstName(), student.getLastName()))
                            .toList();

                    return new CourseResponse(
                            course.getCode(),
                            course.getTitle(),
                            course.getDescription(),
                            studentList
                    );
                })
                .toList();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Course> getCourseByCode(@PathVariable String code) {
        return courseRepository.findById(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{code}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable String code,
            @RequestBody Course updatedCourse
    ) {
        Optional<Course> optionalCourse = courseRepository.findById(code);

        if (optionalCourse.isPresent()) {
            Course existing = optionalCourse.get();
            existing.setTitle(updatedCourse.getTitle());
            existing.setDescription(updatedCourse.getDescription());
            return ResponseEntity.ok(courseRepository.save(existing));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String code) {
        if (courseRepository.existsById(code)) {
            courseRepository.deleteById(code);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
