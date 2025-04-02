package com.example.course.service;

import com.example.course.model.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        System.out.println("New course created id: " + course.getId());
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        System.out.println("Get all courses");
        return courses;
    }

    public Optional<Course> getCourse(int id) {
        System.out.println("Get course by id: " + id);
        return courses.stream()
                .filter(course -> course.getId() == id)
                .findFirst();
    }

    public boolean updateCourse(int id, Course updatedCourse) {
        Optional<Course> optional = getCourse(id);
        if (optional.isPresent()) {
            System.out.println("Course updated id: " + id);
            Course course = optional.get();
            course.setName(updatedCourse.getName());
            course.setPrice(updatedCourse.getPrice());
            return true;
        } else {
            System.out.println("Course isn't updated id: " + id);
            return false;
        }
    }

    public boolean deleteCourse(int id) {
        System.out.println("Course deleted id: " + id);
        return courses.removeIf(course -> course.getId() == id);
    }

}
