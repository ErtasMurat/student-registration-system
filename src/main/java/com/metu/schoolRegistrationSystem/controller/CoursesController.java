package com.metu.schoolRegistrationSystem.controller;

import java.util.List;
import java.util.Optional;

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

import com.metu.schoolRegistrationSystem.model.Course;
import com.metu.schoolRegistrationSystem.service.CourseService;

@RestController
@RequestMapping(value = "/api")
public class CoursesController {

	@Autowired
	private CourseService courseService;

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAll() {
		try {
			List<Course> courses = courseService.findAll();

			if (courses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(courses, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getById(@PathVariable("id") long id) {
		Optional<Course> courseData = courseService.findById(id);

		if (courseData.isPresent()) {
			return new ResponseEntity<>(courseData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/courses")
	public ResponseEntity<Course> create(@RequestBody Course course) {
		try {
			Course _course = courseService.save(new Course(course.getCourseName()));
			return new ResponseEntity<>(_course, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> update(@PathVariable("id") long id, @RequestBody Course course) {
		Optional<Course> courseData = courseService.findById(id);

		if (courseData.isPresent()) {
			Course _course = courseData.get();
			_course.setCourseName(course.getCourseName());
			return new ResponseEntity<>(courseService.save(_course), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/courses/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			courseService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/courses")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			courseService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
