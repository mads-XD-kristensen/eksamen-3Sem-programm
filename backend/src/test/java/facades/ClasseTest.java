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
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Mads
 */
public class ClasseTest {

    private static EntityManagerFactory emf;
    private static CourseFacade facade = null;
    private static ClasseFacade facade1 = null;

    @BeforeAll
    public static void setUpClass() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CourseFacade.getCourseFacade(emf);
        facade1 = ClasseFacade.getClasseFacade(emf);
    }

    @BeforeEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Classe").executeUpdate();
        em.createQuery("DELETE FROM Course").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    public void testAddCourse() throws InvalidInputException {
        EntityManager em = emf.createEntityManager();

        CourseDTO courseDTO = new CourseDTO("Math", "Math basics");

        facade.addCourse(courseDTO);
        
        Classe classe = new Classe("3.rd semester", 5);

        facade1.addClasse(courseDTO.getCourseName(), classe);
       
        assertThat(classe.getCourse().getCourseName(), equalTo(courseDTO.getCourseName()));
        
    }

}
