package com.example.security.controller;

import com.example.security.model.Student;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> Students= Arrays.asList(
            new Student(1,"James Bond"),
            new Student(2,"Maria Jones"),
            new Student(3,"Ana,Smith")
    );
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public List<Student>getAll(){
        return Students;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void register(@RequestBody Student s){
        System.out.println(s);
    }
    @DeleteMapping(path="{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void delete(@PathVariable Integer id){
        System.out.println(id);
    }
    @PutMapping(path="{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public void update(@RequestBody Integer id,Student s){
        System.out.println(String.format("%s %s",id,s));
    }
}
