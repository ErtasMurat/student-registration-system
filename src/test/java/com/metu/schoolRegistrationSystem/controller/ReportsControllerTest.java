package com.metu.schoolRegistrationSystem.controller;

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
public class ReportsControllerTest {
	private MockMvc mockMvc;
	@InjectMocks
	ReportsController reportsController;
	@Mock
	StudentService studentService;
	@Mock
	CourseService courseService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(reportsController).build();
	}

	@Test
	public void getCourseStudents() throws Exception {
		Student testStudent = new Student("Student name");
        Course testCourse = new Course("Test course");
        testStudent.getCourses().add(testCourse);
        
        Optional<Student> student = Optional.of(testStudent);
        Mockito.when(studentService.findById(1l)).thenReturn(student);
        Optional<Course> course = Optional.of(testCourse);
        Mockito.when(courseService.findById(1l)).thenReturn(course);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/reports/course/1/students"))
                .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getStudentCourses() throws Exception {
		Student testStudent = new Student("Student name");
		Course testCourse = new Course("Test course");
		testStudent.getCourses().add(testCourse);

		Optional<Student> student = Optional.of(testStudent);
		Mockito.when(studentService.findById(1l)).thenReturn(student);
		Optional<Course> course = Optional.of(testCourse);
		Mockito.when(courseService.findById(1l)).thenReturn(course);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/reports/student/1/courses"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

}
