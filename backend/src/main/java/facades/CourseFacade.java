/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.CourseDTO;
import entities.Course;
import errorhandling.InvalidInputException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mads
 */
public class CourseFacade {

    private static EntityManagerFactory emf;
    private static CourseFacade instance;

    private CourseFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static CourseFacade getCourseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CourseFacade();
        }
        return instance;
    }

    public CourseDTO addCourse(CourseDTO courseDTO) throws InvalidInputException {
        EntityManager em = emf.createEntityManager();
        String name = null;
        try {
            Query query = em.createQuery("SELECT c.courseName FROM Course c WHERE c.courseName = :name");
            query.setParameter("name", courseDTO.getCourseName());
            name = (String) query.getSingleResult();
        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            throw new InvalidInputException("The Course name is already taken");
        }

        if (name != null) {
            throw new InvalidInputException("The Course name is already taken");

        }
        
        Course course = new Course(courseDTO.getCourseName(), courseDTO.getDescription());
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();

        return courseDTO;
    }

    public List<CourseDTO> allCourses() {
        List<CourseDTO> listOfAllCourses = new ArrayList<>();
        List<Course> courseList = new ArrayList<>();
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Course> q1 = em.createQuery("SELECT c FROM Course c", Course.class
            );
            courseList = q1.getResultList();
        } finally {
            em.close();
        }

        for (Course c : courseList) {
            listOfAllCourses.add(new CourseDTO(c));
        }

        return listOfAllCourses;
    }

}
