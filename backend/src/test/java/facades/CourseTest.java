/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import DTOs.CourseDTO;
import com.google.gson.Gson;
import entities.Course;
import errorhandling.InvalidInputException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author Mads
 */
public class CourseTest {

    private static EntityManagerFactory emf;
    private static CourseFacade facade = null;

    @BeforeAll
    public static void setUpClass() throws IOException, InterruptedException, ExecutionException, TimeoutException {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = CourseFacade.getCourseFacade(emf);
    }

    @BeforeEach
    public void tearDown() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Course").executeUpdate();
        em.getTransaction().commit();
    }

    @Test
    public void testAddCourse() throws InvalidInputException {
        EntityManager em = emf.createEntityManager();

        CourseDTO expCourseDTO = new CourseDTO("Matematik", "Lær Matematik, alt fra B niveau");

        facade.addCourse(expCourseDTO);

        Query q1 = em.createQuery("SELECT c FROM Course c");
        Course result = (Course) q1.getSingleResult();
        CourseDTO resultCourseDTO = new CourseDTO(result);

        assertThat(resultCourseDTO.getCourseName(), equalTo(expCourseDTO.getCourseName()));
    }

    
    @Disabled // Meningen med testen er at den ikke skal fejle når der sker en exception, men er usikker på hvordan
    @Test
    public void testAddCourseFailing() throws InvalidInputException {
        EntityManager em = emf.createEntityManager();

        CourseDTO expCourseDTO = new CourseDTO("Matematik", "Lær Matematik, alt fra B niveau");
        CourseDTO expCourseDTO2 = new CourseDTO("Matematik", "Lær Matematik, alt fra A niveau");

        facade.addCourse(expCourseDTO);

        facade.addCourse(expCourseDTO2);

    }

    @Test
    public void testAllCourses() throws InvalidInputException {
        testAddCourse();
        List<CourseDTO> testList = facade.allCourses();

        assertThat(testList.get(0).getCourseName(), equalTo("Matematik"));

    }
}
