package com.example.crudapplication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Customer {

    private String id ;
    private String userName ;
    private double Balance;
}
