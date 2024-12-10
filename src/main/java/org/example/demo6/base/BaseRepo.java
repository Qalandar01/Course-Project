package org.example.demo6.base;

import jakarta.persistence.EntityManager;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.example.demo6.listerner.MyListerner.EMF;

public class BaseRepo<T> {

    private final Class<T> persistenceClass;

    public BaseRepo() {
        Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.persistenceClass = clazz;
    }


    public void save(T entity) {
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<T> findAll(){
        try (
                EntityManager entityManager = EMF.createEntityManager();

        ) {
           return entityManager.createQuery("from %s".formatted(persistenceClass.getSimpleName()),persistenceClass).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
