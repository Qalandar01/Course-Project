package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Groups;
import org.example.demo6.entity.Module;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class GroupRepo {
    public static List<Groups> getGroupsByModuleId(Integer moduleId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("select g from Groups g where g.module.id = :moduleId", Groups.class)
                    .setParameter("moduleId", moduleId)
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
}
