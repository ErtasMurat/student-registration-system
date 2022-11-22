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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.metu.schoolRegistrationSystem.Application;
import com.metu.schoolRegistrationSystem.model.Course;
import com.metu.schoolRegistrationSystem.model.Student;
import com.metu.schoolRegistrationSystem.service.CourseService;
import com.metu.schoolRegistrationSystem.service.StudentService;

@RestController
@RequestMapping(value = "/api")
public class StudentsController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAll() {
		try {
			List<Student> students = studentService.findAll();

			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getById(@PathVariable("id") long id) {
		Optional<Student> studentData = studentService.findById(id);

		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/students")
	public ResponseEntity<Student> create(@RequestBody Student student) {
		try {
			Student _student = studentService.save(new Student(student.getStudentName()));
			return new ResponseEntity<>(_student, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> update(@PathVariable("id") long id, @RequestBody Student student) {
		Optional<Student> studentData = studentService.findById(id);

		if (studentData.isPresent()) {
			Student _student = studentData.get();
			_student.setStudentName(student.getStudentName());
			return new ResponseEntity<>(studentService.save(_student), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
		try {
			studentService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/students")
	public ResponseEntity<HttpStatus> deleteAll() {
		try {
			studentService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/students/{id}/register/{course_id}", method = RequestMethod.POST)
	public ResponseEntity<Student> register(@PathVariable("id") long id, @PathVariable("course_id") long course_id) {
		Optional<Student> studentData = studentService.findById(id);
		Optional<Course> courseData = courseService.findById(course_id);

		if (studentData.isPresent() && courseData.isPresent()) {
			Student _student = studentData.get();
			Course _course = courseData.get();

			if (_student.getCourses().size() < Application.COURSES_LIMIT
					&& _course.getStudents().size() < Application.STUDENTS_LIMIT) {

				_student.getCourses().add(_course);
				return new ResponseEntity<>(studentService.save(_student), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
