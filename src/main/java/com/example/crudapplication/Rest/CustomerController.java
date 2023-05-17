package com.example.crudapplication.Rest;



import com.example.crudapplication.ApiResponse.ApiResponse;
import com.example.crudapplication.Model.Customer;
import com.example.crudapplication.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/vi/customers")
public class CustomerController {
    ArrayList<Customer> customers = new ArrayList<Customer>();
    //read , create , update, delete

    @GetMapping("/get")
    public ArrayList <Customer> getCustomers(){
        return customers;
    }

    @PostMapping("/create")
    public ApiResponse postCustomer(@RequestBody Customer customer){
        if(customer.getBalance()>=0) {
            this.customers.add(customer);
            return new ApiResponse("Customer added");
        }
        return new ApiResponse("Balance cannot be negative");
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateTodo(@PathVariable int id, @RequestBody Customer object){
        customers.set(id,object);
        return new ApiResponse("Customer is updated");
    }

    @DeleteMapping("/delete")
    public ApiResponse deleteCustomer(@RequestBody Customer object){
        if(customers.remove(object))
            return new ApiResponse("Customer is removed");

        return new ApiResponse("Customer dosent exist");
    }

    @PutMapping ("/deposit/{id}")
    public ApiResponse Deposit(@PathVariable int id , @RequestBody double money){
        customers.get(id).setBalance(customers.get(id).getBalance()+money);

        return new ApiResponse("Deposit is succesfull");
    }

    @PutMapping("/withdraw/{id}")
    public ApiResponse Withdraw(@PathVariable int id , @RequestBody double money){
        if(customers.get(id).getBalance() <money)
            return new ApiResponse("Customer dosent have enough balance");

        customers.get(id).setBalance(customers.get(id).getBalance()-money);
        return new ApiResponse("Withdraw is succesfull");
    }



}
