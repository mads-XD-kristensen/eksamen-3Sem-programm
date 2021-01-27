/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.CourseDTO;
import entities.Course;
import errorhandling.InvalidInputException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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
    
    
    public CourseDTO addCourse (CourseDTO courseDTO) throws InvalidInputException {
        EntityManager em = emf.createEntityManager();
        String name = null;
        try {
            Query query = em.createQuery("SELECT c.course_name FROM course c WHERE c.course_name = :name");
            query.setParameter("name", courseDTO.getCourseName());
            name = (String) query.getSingleResult();
        } catch (Exception e) {}
        if (name != null) {
            throw new InvalidInputException(String.format("The name %s is already taken", name));
        }
        
        Course course = new Course(courseDTO.getCourseName(), courseDTO.getDescription());
        em.getTransaction().begin();
        em.persist(course);
        em.getTransaction().commit();
        
        return new CourseDTO(courseDTO.getCourseName(), courseDTO.getDescription());
    }
    
    
    
    
    
    
}
