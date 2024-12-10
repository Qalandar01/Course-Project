package org.example.demo6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo6.base.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Payment extends BaseEntity {

    private Integer amount;

    @CreationTimestamp
    private LocalDate date;

    @ManyToOne
    private Student student;

    public Payment(Integer amount, Student student) {
        this.amount = amount;
        this.student = student;
    }
}
