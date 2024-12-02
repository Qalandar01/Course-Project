package org.example.demo6.servlets;

import jakarta.persistence.EntityManager;
import org.example.demo6.entity.Payment;
import org.example.demo6.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.demo6.listerner.MyListerner.EMF;
@WebServlet("/payment/save")
public class AddPaymentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try (
                EntityManager entityManager = EMF.createEntityManager();
        ) {
            int studentId = Integer.parseInt(req.getParameter("studentId"));
            int amount = Integer.parseInt(req.getParameter("amount"));
            Student student = entityManager.find(Student.class, studentId);
            Payment payment = new Payment(amount,student);
            entityManager.getTransaction().begin();
            entityManager.persist(payment);
            entityManager.getTransaction().commit();
            Integer groupId = entityManager.createQuery("select g.id from Groups g join Student s on s.groups.id = g.id where s.id = :studentId ", Integer.class)
                    .setParameter("studentId", studentId).getSingleResult();
            resp.sendRedirect("/student.jsp?groupId=" + groupId);
        }
    }
}
