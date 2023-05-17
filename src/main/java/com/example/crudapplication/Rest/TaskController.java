package com.example.crudapplication.Rest;

import com.example.crudapplication.ApiResponse.ApiResponse;
import com.example.crudapplication.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/vi/tasks")
public class TaskController {
    ArrayList<Task> todos = new ArrayList<Task>();
    //read , create , update, delete

    @GetMapping("/get")
    public ArrayList <Task> getTodo(){
       return todos;
    }

    @PostMapping("/create")
    public ApiResponse postTask(@RequestBody Task todo){
        if(todo.getStatus().equalsIgnoreCase("not done") || todo.getStatus().equalsIgnoreCase("done")) {
            this.todos.add(todo);
            return new ApiResponse("Todo added");
        }
        else{
            return new ApiResponse("task must be done or not done!");
        }
    }

    @PutMapping("/update/{id}")
    public ArrayList <Task> updateTodo(@PathVariable int id, @RequestBody Task object){

        todos.set(id,object);
        return todos;
    }

    @DeleteMapping("/delete")
        public ApiResponse deleteTask(@RequestBody Task object){
        if(!todos.contains(object))
            return new ApiResponse("Task dosent exist");
        todos.remove(object);
        return new ApiResponse("Task is removed");
    }

    @PutMapping ("/status/{id}")
    public ApiResponse StatusTask(@PathVariable int id , @RequestBody String status){
        if(status.equalsIgnoreCase("not done") || status.equalsIgnoreCase("done")) {
            todos.get(id).setStatus(status);
            return new ApiResponse("Status changed");
        }

        return new ApiResponse("Invalid entry!");
    }

    @GetMapping("/search")
    public Object searchTask(@RequestBody String title){
        for(Task task : todos)
            if(task.getTitle().equalsIgnoreCase(title))
                return task;
        return new ApiResponse("Task with this title dosent exist");
    }



}
