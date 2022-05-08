package com.cs.test05.controller;

import com.cs.test05.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
//    @Qualifier("userService2")
    private IUserService userService1;

    @Autowired
    private List<IUserService> list;

    @Autowired
    private Map<String, IUserService> map;

    // 使用构造注入
//    public UserController(IUserService userService) {
//        this.userService = userService;
//    }

    public void print() {
        userService1.print();
    }

    public void doList() {
        for (IUserService service : list) {
            service.print();
        }
    }

    public void doMap() {
        for (String s : map.keySet()) {
            System.out.println(s + "  :  " + map.get(s));
        }
    }
}
