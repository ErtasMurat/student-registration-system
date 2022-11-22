package com.metu.schoolRegistrationSystem.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metu.schoolRegistrationSystem.dao.StudentRepository;
import com.metu.schoolRegistrationSystem.model.Student;

@Service
public class StudentService {
	private final static Logger LOG = LoggerFactory.getLogger(StudentService.class);

	private StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}

	public Optional<Student> findById(long id) {
		return studentRepository.findById(id);
	}

	public Student save(Student student) {
		LOG.info("Student {} has been successfully added.", student.getStudentId());
		return studentRepository.save(student);
	}

	public void deleteById(long id) {
		LOG.info("Student: {} has been successfully deleted.", id);
		studentRepository.deleteById(id);

	}

	public void deleteAll() {
		studentRepository.deleteAll();

	}

}
