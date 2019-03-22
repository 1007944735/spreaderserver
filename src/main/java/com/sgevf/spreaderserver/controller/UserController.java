package com.sgevf.spreaderserver.controller;

import com.sgevf.spreaderserver.entity.User;
import com.sgevf.spreaderserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @ResponseBody
    @RequestMapping(value = "/user/find/{id}", method = RequestMethod.GET)
    public User findUser(@PathVariable("id") Integer id) {
        return service.findUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/user/insert/{name}/{age}", method = RequestMethod.GET)
    public Integer findUser(@PathVariable("name") String name, @PathVariable("age") String age) {
        return service.insertUser(name, age);
    }
}
