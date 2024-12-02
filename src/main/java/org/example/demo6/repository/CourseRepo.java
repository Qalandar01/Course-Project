package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Course;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class CourseRepo {

    public static List<Course> getAllCourses() {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
           return entityManager.createQuery("from Course ",Course.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
