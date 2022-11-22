package com.metu.schoolRegistrationSystem.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.metu.schoolRegistrationSystem.model.Course;
import com.metu.schoolRegistrationSystem.model.Student;
import com.metu.schoolRegistrationSystem.service.CourseService;
import com.metu.schoolRegistrationSystem.service.StudentService;

@RunWith(SpringRunner.class)
public class StudentsControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	StudentsController studentsController;
	@Mock
	StudentService studentService;
	@Mock
	CourseService courseService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(studentsController).build();
	}

	@Test
	public void getAll() throws Exception {
		List<Student> students = Arrays.asList(new Student("Student name"));
		Mockito.when(studentService.findAll()).thenReturn(students);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/students")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getById() throws Exception {
		Optional<Student> student = Optional.of(new Student("Student name"));
		Mockito.when(studentService.findById(1l)).thenReturn(student);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/students/1")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void delete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/students/1"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

	}

	@Test
	public void register() throws Exception {
		Optional<Student> student = Optional.of(new Student("Student name"));
		Mockito.when(studentService.findById(1l)).thenReturn(student);
		Optional<Course> course = Optional.of(new Course("Test course"));
		Mockito.when(courseService.findById(1l)).thenReturn(course);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/students/1/register/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}
}
