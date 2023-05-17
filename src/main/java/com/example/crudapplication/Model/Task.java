package com.example.crudapplication.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

import java.util.concurrent.atomic.AtomicInteger;

@AllArgsConstructor
@Data
public class Task {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
    private String id =  Integer.toString(ID_GENERATOR.getAndIncrement());
    private String title;
    private String desc;
    private String status;

}
