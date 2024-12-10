package org.example.demo6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.demo6.base.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Groups extends BaseEntity {

    private String name;

    @ManyToOne
    private Module module;
}
