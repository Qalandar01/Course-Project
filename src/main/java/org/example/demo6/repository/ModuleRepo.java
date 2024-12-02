package org.example.demo6.repository;

import jakarta.persistence.EntityManager;

import org.example.demo6.entity.Module;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class ModuleRepo {

    public static List<Module> getModulesByCourseId(Integer courseId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            String jpql = "SELECT m FROM Module m WHERE m.course.id = :courseId";
            return entityManager.createQuery(jpql, Module.class)
                    .setParameter("courseId", courseId)
                    .getResultList();

        }
    }
}
