package com.example.springvalidation.Model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.springframework.boot.jackson.JsonMixin;

import java.util.Date;

@AllArgsConstructor
@Data
public class Employee {

    public static final String oneAtt = "one";
    public static final String twoAtt = "two";


    @NotEmpty(message = "id should not be empty")
    @Size(min = 2 , message = "ID should be longer than 2 digits! ")
    private String id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 4 , message = "username should be longer than 4 charcters! ")
    private String name;
    @NotNull(message = "age should not be empty")
    @Min(25)
    private long age;
    @NotEmpty(message = "position should not be empty")
    @Pattern(regexp = "(supervisor|coordinator)", message = "position dosent exist (must be supervisor or coordinator !)")
    private String position;
    @AssertFalse
    private boolean onLeave ;
    @NotNull(message = "employment year should not be empty")
    @Positive(message = "must be a number!")
    @Min(1950)
    private Integer employmentYear;
    @NotNull(message = "annual leave  should not be empty")
    @PositiveOrZero(message = "must be a number!")
    private int annualLeave;
}
