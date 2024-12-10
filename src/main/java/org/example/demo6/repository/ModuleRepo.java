package org.example.demo6.repository;

import jakarta.persistence.EntityManager;

import org.example.demo6.entity.Module;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class ModuleRepo {

    public static List<Module> getModulesByCourseId(Integer courseId, String search, Integer pages) {
        pages--;
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            String jpql = "SELECT m FROM Module m WHERE m.course.id = :courseId and m.name ilike :search";
            return entityManager.createQuery(jpql, Module.class)
                    .setParameter("courseId", courseId)
                    .setParameter("search", "%" + search + "%")
                    .setFirstResult(pages*5)
                    .setMaxResults(5)
                    .getResultList();

        }
    }

    public static Integer getCourseId(Integer moduleId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            return entityManager.createQuery("select c.id from Course c join Module m on m.course.id = c.id where m.id = :moduleId", Integer.class)
                    .setParameter("moduleId", moduleId).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Long count(Integer courseId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            return (Long) entityManager.createNativeQuery("select count(m.id) from module m join course c on m.course_id = c.id where c.id = : courseId", Long.class)
                    .setParameter("courseId", courseId)
                    .getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
