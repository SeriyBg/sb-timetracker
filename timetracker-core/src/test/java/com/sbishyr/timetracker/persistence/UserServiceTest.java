package com.sbishyr.timetracker.persistence;

import com.sbishyr.timetracker.persistence.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User("username", "firstName", "secondName");
    }

    @Test
    public void shouldCreateNewUser() throws Exception {
        User savedUser = userService.save(user);

        assertThat(savedUser).isEqualTo(user);
        assertThat(savedUser.getId()).isNotEqualTo(0L);
    }

    @Test
    public void shouldFindANewCreatedUser() throws Exception {
        User savedUser = userService.save(user);

        User foundUser = userService.findOne(savedUser.getId());

        assertThat(foundUser).isEqualTo(savedUser);
    }
}
