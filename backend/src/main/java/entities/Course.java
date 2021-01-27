/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Mads
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "course", cascade = CascadeType.PERSIST)
    List<Classe> classes;

    public Course() {
    }

    public Course(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
        this.classes = new ArrayList<>();
    }

    public List<Classe> getClasses() {
        return classes;
    }

    public void addClasse(Classe classe) {
        this.classes.add(classe);
        if (classe != null) {
            classe.setCourse(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
