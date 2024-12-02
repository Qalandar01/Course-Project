package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Groups;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class GroupRepo {
    public static List<Groups> getGroupsByModuleId(Integer moduleId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
           return entityManager.createQuery("select g from Groups g where g.module.id = :moduleId",Groups.class)
                    .setParameter("moduleId",moduleId)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
