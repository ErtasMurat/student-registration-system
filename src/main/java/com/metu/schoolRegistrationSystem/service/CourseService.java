package com.metu.schoolRegistrationSystem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metu.schoolRegistrationSystem.dao.CourseRepository;
import com.metu.schoolRegistrationSystem.model.Course;

@Service
public class CourseService {
	private final static Logger LOG = LoggerFactory.getLogger(CourseService.class);

	private CourseRepository courseRepository;

	@Autowired
	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> findAll() {
		return courseRepository.findAll();
	}

	public Optional<Course> findById(long id) {
		return courseRepository.findById(id);
	}

	public Course save(Course course) {
		LOG.info("Course: {} has been successfully added.", course.getCourseId());
		return courseRepository.save(course);
	}

	public void deleteById(long id) {
		LOG.info("Course: {} has been successfully deleted.", id);
		courseRepository.deleteById(id);

	}

	public void deleteAll() {
		courseRepository.deleteAll();

	}

}
