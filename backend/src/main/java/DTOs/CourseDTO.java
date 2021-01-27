/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entities.Course;

/**
 *
 * @author Mads
 */
public class CourseDTO {
    String courseName;
    String description;
    
    public CourseDTO(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }
    
    public CourseDTO(Course course){
        this.courseName = course.getCourseName();
        this.description = course.getDescription();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }
    
    
}
