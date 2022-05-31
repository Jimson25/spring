package com.cs.bootweb.comtroller;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user")
    public String get(){
        return "GET-USER";
    }

    @PostMapping("/user")
    public String save(){
        return "POST-USER";
    }

    @DeleteMapping("/user")
    public String delete(){
        return "DELETE-USER";
    }

    @PutMapping("/user")
    public String update(){
        return "PUT-USER";
    }
}
