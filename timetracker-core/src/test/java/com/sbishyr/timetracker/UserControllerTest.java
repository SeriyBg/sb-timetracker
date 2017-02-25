package com.sbishyr.timetracker;

import com.sbishyr.timetracker.persistence.UserService;
import com.sbishyr.timetracker.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    @Autowired
    private UserController controller;

    private MockMvc mockMvc;

    @Before
    public void setUpController() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldReturnAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("username", "firstName", "lastName"));
        users.add(new User("username2", "firstName2", "lastName2"));
        when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("username")))
                .andExpect(jsonPath("$[0].firstName", is("firstName")))
                .andExpect(jsonPath("$[0].lastName", is("lastName")))
                .andExpect(jsonPath("$[1].username", is("username2")))
                .andExpect(jsonPath("$[1].firstName", is("firstName2")))
                .andExpect(jsonPath("$[1].lastName", is("lastName2")));

    }
}
