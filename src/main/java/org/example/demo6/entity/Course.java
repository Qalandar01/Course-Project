package org.example.demo6.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.demo6.base.BaseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Course extends BaseEntity {

    private String name;
}
