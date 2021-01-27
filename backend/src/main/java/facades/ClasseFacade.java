/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.ClasseDTO;
import DTOs.CourseDTO;
import entities.Classe;
import entities.Course;
import errorhandling.InvalidInputException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Mads
 */
public class ClasseFacade {

    private static EntityManagerFactory emf;
    private static ClasseFacade instance;

    private ClasseFacade() {
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static ClasseFacade getClasseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new ClasseFacade();
        }
        return instance;
    }

    public ClasseDTO addClasse(String courseName, Classe classe) throws InvalidInputException {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<Course> query = em.createQuery("SELECT c FROM Course c WHERE c.courseName = :name", Course.class);
            query.setParameter("name", courseName);
            Course course = query.getSingleResult();

            if (course == null) {
                throw new InvalidInputException("Given class doesnt exist");
            }
            course.addClasse(classe);
            em.getTransaction().begin();
            em.persist(course);
            em.getTransaction().commit();
        } catch (Exception e) {
        }
        return new ClasseDTO(classe);
    }

}
