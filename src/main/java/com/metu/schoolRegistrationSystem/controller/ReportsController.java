package com.metu.schoolRegistrationSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metu.schoolRegistrationSystem.model.Course;
import com.metu.schoolRegistrationSystem.model.Student;
import com.metu.schoolRegistrationSystem.service.CourseService;
import com.metu.schoolRegistrationSystem.service.StudentService;

@RestController
@RequestMapping(value = "/api")
public class ReportsController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/reports/course/{id}/students")
	public ResponseEntity<List<Student>> getCourseStudents(@PathVariable("id") long id) {
		Optional<Course> courseData = courseService.findById(id);

		if (courseData.isPresent()) {
			Course course = courseData.get();
			return new ResponseEntity<>(new ArrayList<Student>(course.getStudents()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/reports/student/{id}/courses")
	public ResponseEntity<List<Course>> getStudentCourses(@PathVariable("id") long id) {
		Optional<Student> studentData = studentService.findById(id);

		if (studentData.isPresent()) {
			Student student = studentData.get();
			return new ResponseEntity<>(new ArrayList<Course>(student.getCourses()), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
