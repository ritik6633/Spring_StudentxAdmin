package com.example.crud.service;

import com.example.crud.model.Admin;
import com.example.crud.repository.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepo rep;

    public Admin saveAdmin(Admin admin){
        Admin a = rep.save(admin);
        return a;
    }

    public Admin getAdminByEmail(String email){
        Admin ad = rep.findByEmail(email);
        return ad;
    }
}
