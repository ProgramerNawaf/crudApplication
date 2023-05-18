package com.example.springvalidation.Controller;

import com.example.springvalidation.ApiResponse.ApiResponse;
import com.example.springvalidation.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<Employee>();

    @GetMapping("/get")
    public ArrayList<Employee> getEmployees() {
        return employees;
    }
    @PostMapping("/create")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee , Errors e){
        if(e.hasErrors()) {
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body("Employee added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateEmployee(@Valid @RequestBody Employee user , Errors e , @PathVariable int id){
        if(e.hasErrors()) {
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.set(id,user);
        return ResponseEntity.status(200).body("Employee updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteEmployee(Errors e ,@PathVariable int id ){
        if(e.hasErrors()) {
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        employees.remove(employees.get(id));return ResponseEntity.status(HttpStatus.OK).body("Employee deleted");
    }

    @PutMapping("/leave/{id}")
    public ResponseEntity leave( @PathVariable int id){

        if(employees.get(id).isOnLeave())
            return  ResponseEntity.status(400).body( new ApiResponse("Employee is already on leave!"));

        if(employees.get(id).getAnnualLeave() >0) {
            employees.get(id).setOnLeave(true);
            employees.get(id).setAnnualLeave(employees.get(id).getAnnualLeave() - 1);
            return ResponseEntity.status(200).body(new ApiResponse("Employee is now on leave!"));
        }
        return  ResponseEntity.status(400).body( new ApiResponse("you dont have enough days to take a leave!"));
    }

    @PutMapping("/vacation/{id}")
    public ResponseEntity vacation(@RequestBody int leaveDays , Errors e , @PathVariable int id){
        if(e.hasErrors()) {
            String message = e.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        if(employees.get(id).isOnLeave())
            return  ResponseEntity.status(400).body( new ApiResponse("Employee is already on leave!"));

        if(employees.get(id).getAnnualLeave() >=leaveDays) {
            employees.get(id).setOnLeave(true);
            employees.get(id).setAnnualLeave(employees.get(id).getAnnualLeave() - leaveDays);
            return ResponseEntity.status(200).body(new ApiResponse("Employee is now on vacation!"));
        }
        return  ResponseEntity.status(400).body( new ApiResponse("you dont have enough days to take a vacation!"));
    }

}
