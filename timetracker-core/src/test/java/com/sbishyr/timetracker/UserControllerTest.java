package com.sbishyr.timetracker;

import com.sbishyr.timetracker.persistence.UserService;
import com.sbishyr.timetracker.persistence.entity.User;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("username")))
                .andExpect(jsonPath("$[0].firstName", is("firstName")))
                .andExpect(jsonPath("$[0].lastName", is("lastName")))
                .andExpect(jsonPath("$[1].username", is("username2")))
                .andExpect(jsonPath("$[1].firstName", is("firstName2")))
                .andExpect(jsonPath("$[1].lastName", is("lastName2")));
    }

    @Test
    public void shouldReturnUserById() throws Exception {
        User user = new User("username", "firstName", "lastName");
        when(userService.findOne(anyLong())).thenReturn(user);

        mockMvc.perform(get("/user/{id}", 42L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("username")))
                .andExpect(jsonPath("$.firstName", is("firstName")))
                .andExpect(jsonPath("$.lastName", is("lastName")));
    }

    @Test
    public void shouldSaveNewReceivedUser() throws Exception {
        User user = new User("username", "firstName", "lastName");
        user.setId(42L);
        JSONObject newUserAsJson = new JSONObject();
        newUserAsJson.accumulate("username", user.getUsername());
        newUserAsJson.accumulate("firstName", user.getFirstName());
        newUserAsJson.accumulate("lastName", user.getLastName());

        when(userService.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserAsJson.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(42)))
                .andExpect(jsonPath("$.username", is("username")))
                .andExpect(jsonPath("$.firstName", is("firstName")))
                .andExpect(jsonPath("$.lastName", is("lastName")));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/{id}", 42))
                .andExpect(status().isOk());
    }
}
