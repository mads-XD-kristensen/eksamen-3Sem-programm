/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTOs;

import entities.Classe;

/**
 *
 * @author Mads
 */
public class ClasseDTO {

    private String courseName;
    private String semester;
    private int numberOfStudents;

    public ClasseDTO(String semester, int numberOfStudents) {
        this.semester = semester;
        this.numberOfStudents = numberOfStudents;
    }

    public ClasseDTO(Classe classe) {
        this.semester = classe.getSemester();
        this.numberOfStudents = classe.getNumberOfStudents();
    }

    public ClasseDTO(String courseName, String semester, int numberOfStudents) {
        this.courseName = courseName;
        this.semester = semester;
        this.numberOfStudents = numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(int numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

}
