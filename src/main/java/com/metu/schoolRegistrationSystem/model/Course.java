package com.metu.schoolRegistrationSystem.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course implements Serializable {

	private static final long serialVersionUID = -8950619527509698586L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COURSE_ID", unique = true, nullable = false, length = 20)
	private Long courseId;

	@Column(name = "COURSE_NAME")
	@NotEmpty
	private String courseName;

	@Column(name = "COURSE_DETAILS")
	private String courseDetails;

	@Column(name = "COURSE_DURATION")
	private String courseDuration;

	@ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
	@JsonIgnore
	private Set<Student> students = new HashSet<>();

	public Course(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseDetails == null) ? 0 : courseDetails.hashCode());
		result = prime * result + ((courseDuration == null) ? 0 : courseDuration.hashCode());
		result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseDetails == null) {
			if (other.courseDetails != null)
				return false;
		} else if (!courseDetails.equals(other.courseDetails))
			return false;
		if (courseDuration == null) {
			if (other.courseDuration != null)
				return false;
		} else if (!courseDuration.equals(other.courseDuration))
			return false;
		if (courseId == null) {
			if (other.courseId != null)
				return false;
		} else if (!courseId.equals(other.courseId))
			return false;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDetails=" + courseDetails
				+ ", courseDuration=" + courseDuration + "]";
	}

}
