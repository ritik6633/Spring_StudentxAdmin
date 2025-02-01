package com.example.crud.controller;

import com.example.crud.model.Admin;
import com.example.crud.model.Students;
import com.example.crud.service.AdminService;
import com.example.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MyController {

    private boolean isLogin = false;


    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @GetMapping("/register")
    public ModelAndView loadRegister(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        return modelAndView;
    }




    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @PostMapping("/saveAdmin")
    public ModelAndView saveAdmin(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("email") String email){
        ModelAndView modelAndView = new ModelAndView();
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        admin.setEmail(email);
        adminService.saveAdmin(admin);
        modelAndView.setViewName("login");
        return modelAndView;
    }



    @PostMapping("/loginSucess")
    public ModelAndView loginSucess(
            @RequestParam("email") String email,
            @RequestParam("password") String pass

    ){
        ModelAndView mv = new ModelAndView();
        Admin existedAdmin = adminService.getAdminByEmail(email);
        if(existedAdmin == null){
            mv.setViewName("login");
            mv.addObject("message", "Admin not found");
            return mv;
        }
        else{
            if(existedAdmin.getPassword().equals(pass)){
                isLogin = true;
                mv.setViewName("studentRegistration");
                return mv;
            }
            else{
                mv.setViewName("login");
                mv.addObject("message", "Password is incorrect");
                return mv;
            }
        }

    }



    @GetMapping("/studentRegistration")
    public ModelAndView StudentRegistration(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentRegistration");
        return modelAndView;
    }


    @PostMapping("/saveStudent")
    public ModelAndView saveStudent(@RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("age") String age,
                                    @RequestParam("address") String address){

        ModelAndView modelAndView = new ModelAndView();
        Students stu = new Students();
        stu.setName(name);
        stu.setEmail(email);
        stu.setPhone(phone);
        stu.setAge(Integer.parseInt(age));
        stu.setAddress(address);
        Students count =  studentService.saveStudent(stu);
        modelAndView.setViewName("studentRegistration");
        if(count != null){
            modelAndView.addObject("message", "Student Registered Successfully");
        }
        else{
            modelAndView.addObject("message", "Student Registration Failed");
        }
        return modelAndView;
    }




    @GetMapping("/viewStudentsDetails")
    public ModelAndView getStudentsDetails(){
        ModelAndView modelAndView = new ModelAndView();
        if(!isLogin){
            modelAndView.setViewName("login");
            return modelAndView;
        }
        List<Students> list = studentService.getAllStudents();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("viewStudentsDetails");
        return modelAndView;
    }



    @GetMapping("/editStudent/{id}")
    public ModelAndView editStudent(@PathVariable(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Students student = studentService.getStudentById(id);
        modelAndView.addObject("student", student);
        modelAndView.setViewName("editStudent");
        return modelAndView;
    }



    @PostMapping("/updateStudent")
    public ModelAndView updateStudent(@RequestParam("id") int id,
                                      @RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("phone") String phone,
                                      @RequestParam("age") String age,
                                      @RequestParam("address") String address){
        ModelAndView modelAndView = new ModelAndView();
        Students student = new Students();
        student.setId(id);
        student.setName(name);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAge(Integer.parseInt(age));
        student.setAddress(address);
        studentService.saveStudent(student);
        List<Students> list = studentService.getAllStudents();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("viewStudentsDetails");
        return modelAndView;
    }



    @GetMapping("/deleteStudent/{id}")
    public ModelAndView deleteStudent(@PathVariable(value = "id") int id ){
        ModelAndView modelAndView = new ModelAndView();
        studentService.deleteStudent(id);
        List<Students> list = studentService.getAllStudents();
        modelAndView.addObject("list", list);
        modelAndView.setViewName("viewStudentsDetails");
        return modelAndView;
    }


}

