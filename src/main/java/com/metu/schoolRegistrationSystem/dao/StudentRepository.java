package com.metu.schoolRegistrationSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metu.schoolRegistrationSystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
