package org.example.demo6.listerner;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyListerner implements ServletContextListener {
    public static EntityManagerFactory EMF;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
         EMF = Persistence.createEntityManagerFactory("default");
    }
}
