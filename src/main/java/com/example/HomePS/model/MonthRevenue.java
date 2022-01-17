package com.example.HomePS.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MonthRevenue {
    private String month;
    private Double revenue;

}