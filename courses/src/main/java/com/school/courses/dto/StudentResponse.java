package com.school.courses.dto;

import java.util.List;

public class StudentResponse {
    private String firstName;
    private String lastName;
    private List<CourseSummaryResponse> courses;

    public StudentResponse(String firstName, String lastName, List<CourseSummaryResponse> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<CourseSummaryResponse> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseSummaryResponse> courses) {
        this.courses = courses;
    }
}
