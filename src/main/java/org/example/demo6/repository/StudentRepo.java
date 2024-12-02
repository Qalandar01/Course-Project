package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Student;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class StudentRepo {
    public static List<Student> getStudentsByGroupId(Integer groupId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("select s from Student s where s.groups.id = :groupId", Student.class)
                    .setParameter("groupId", groupId)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Long getStudentTotalPayments(Student student) {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("select sum(p.amount) from Payment p where p.student = :student", Long.class)
                    .setParameter("student", student).getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
