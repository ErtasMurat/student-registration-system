package com.metu.schoolRegistrationSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metu.schoolRegistrationSystem.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
