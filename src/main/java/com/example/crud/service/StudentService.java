package com.example.crud.service;

import com.example.crud.model.Students;
import com.example.crud.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    Repo rep ;
    public Students saveStudent(Students student){
        Students s = rep.save(student);
        return s;
    }

    public List<Students> getAllStudents(){
        List<Students> list = rep.findAll();
        return list;
    }

    public Students getStudentById(int id){
        if(rep.findById(id).isPresent()){
            return rep.findById(id).get();
        }
        else{
            return null;
        }
    }

    public void deleteStudent(int id){
        rep.deleteById(id);
    }
}
