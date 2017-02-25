package com.sbishyr.timetracker;

import com.sbishyr.timetracker.persistence.UserService;
import com.sbishyr.timetracker.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(name = "/user")
    public Iterable<User> getUsers() {
        return userService.findAll();
    }
}
