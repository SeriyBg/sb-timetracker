package com.sbishyr.timetracker;

import com.sbishyr.timetracker.persistence.UserService;
import com.sbishyr.timetracker.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<User> findAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User findOneUser(@PathVariable(name = "id") Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User createNewUser(@RequestBody User user) {
        return userService.save(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
