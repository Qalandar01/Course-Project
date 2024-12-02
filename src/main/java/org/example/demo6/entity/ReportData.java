package org.example.demo6.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReportData {
    private String courseName;
    private Long studentCount;
    private Long totalAmount;
}
