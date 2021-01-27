/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import DTOs.ClasseDTO;
import DTOs.CourseDTO;
import DTOs.UserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Classe;
import errorhandling.InvalidInputException;
import facades.ClasseFacade;
import facades.CourseFacade;
import facades.UserFacade;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import utils.EMF_Creator;

/**
 *
 * @author Mads
 */
@Path("course")
public class CourseResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CourseFacade FACADE = CourseFacade.getCourseFacade(EMF);
    private static final ClasseFacade FACADE1 = ClasseFacade.getClasseFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @Context
    private UriInfo context;

    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public String ShowAllCourses() {
        List<CourseDTO> cdto = FACADE.allCourses();

        return GSON.toJson(cdto);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("add")
    @RolesAllowed("admin")
    public String addCourse(String course) throws InvalidInputException {

        CourseDTO courseDTO = GSON.fromJson(course, CourseDTO.class);
        courseDTO = new CourseDTO(courseDTO.getCourseName(), courseDTO.getDescription());
        courseDTO = FACADE.addCourse(courseDTO);

        return GSON.toJson(courseDTO);
    }

    
    // Dette burde være i en ny fil da det tilhøre en anden entitet, men...
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("classe")
    @RolesAllowed("admin")
    public String addClasseEndpoint(String info) throws InvalidInputException {

        ClasseDTO classeDTO = GSON.fromJson(info, ClasseDTO.class);
        Classe classe = new Classe(classeDTO.getSemester(), classeDTO.getNumberOfStudents());
        FACADE1.addClasse(classeDTO.getCourseName(), classe);
        
        return GSON.toJson(new ClasseDTO(classe));
    }

}
