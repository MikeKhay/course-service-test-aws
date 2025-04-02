package com.example.course.controller;

import com.example.course.model.Course;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity
                .ok(course);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity
                .ok(courses);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Course> getCourse(@PathVariable int id) {
        Optional<Course> course = courseService.getCourse(id);
        if (course.isPresent()) {
            return ResponseEntity.ok(course.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Course> updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        boolean updated = courseService.updateCourse(id, updatedCourse);
        if (updated) {
            updatedCourse.setId(id);
            return ResponseEntity.ok(updatedCourse);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        boolean deleted = courseService.deleteCourse(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "UP";
    }

}
