package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Student;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class StudentRepo {
    public static List<Student> getStudentsByGroupId(Integer groupId, String search, Integer pages) {
        pages--;
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("select s from Student s where s.groups.id = :groupId and s.firstName ilike :search", Student.class)
                    .setParameter("groupId", groupId)
                    .setParameter("search","%"+search+"%")
                    .setFirstResult(pages*5)
                    .setMaxResults(5)
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

    public static Long count(Integer groupId) {
        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            return (Long) entityManager.createNativeQuery("select count(s.id) from student s join groups g on g.id = s.groups_id where g.id = : groupId", Long.class)
                    .setParameter("groupId", groupId)
                    .getSingleResult();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
