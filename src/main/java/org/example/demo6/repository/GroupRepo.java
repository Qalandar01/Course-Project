package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Groups;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class GroupRepo {
    public static List<Groups> getGroupsByModuleId(Integer moduleId, String search, Integer pages) {
        pages--;
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("from Groups g where g.module.id = :moduleId and g.name ilike :search", Groups.class)
                    .setParameter("moduleId", moduleId)
                    .setParameter("search","%" +search+ "%")
                    .setFirstResult(pages*5)
                    .setMaxResults(5)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getModuleId(Integer groupId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            return entityManager.createQuery("select m.id from Module m join Groups g on m.id = g.module.id where g.id = :groupId", Integer.class)
                    .setParameter("groupId", groupId).getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Long count(Integer moduleId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            return (Long)entityManager.createNativeQuery("select count(g.id) from groups g join module m on m.id = g.module_id where m.id = : moduleId", Long.class)
                    .setParameter("moduleId",moduleId)
                    .getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
