package org.example.demo6.repository;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Course;
import org.example.demo6.entity.ReportData;

import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class CourseRepo {

    public static List<Course> getAllCourses(Integer pages, String search) {
        pages--;
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("from Course c where c.name ilike :search", Course.class)
                    .setParameter("search","%" + search + "%")
                    .setFirstResult(pages*5)
                    .setMaxResults(5)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ReportData> getReportDatas() {
        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            return entityManager.createQuery("""
                        select new org.example.demo6.entity.ReportData(
                                                                                                 c.name,
                                                                                                 count(distinct s.id),
                                                                                                 sum(p.amount)
                                                                                             )
                                                                                             from Course c
                                                                                             join Module m on c.id = m.course.id
                                                                                             join Groups g on m.id = g.module.id
                                                                                             join Student s on g.id = s.groups.id
                                                                                             join Payment p on p.student.id = s.id
                                                                                             group by c.name
                    """, ReportData.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Long count() {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            return entityManager.createQuery("select count(c.id) from Course c ", Long.class).getSingleResult();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
