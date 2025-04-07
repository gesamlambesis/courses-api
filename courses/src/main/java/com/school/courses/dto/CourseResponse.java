package com.school.courses.dto;

import java.util.List;

public class CourseResponse {
    private String code;
    private String title;
    private String description;
    private List<StudentSummaryResponse> students;


    public CourseResponse(String code, String title, String description, List<StudentSummaryResponse> students) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.students = students;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StudentSummaryResponse> getStudents() {
        return students;
    }

    public void setStudents(List<StudentSummaryResponse> studentNames) {
        this.students = studentNames;
    }
}
