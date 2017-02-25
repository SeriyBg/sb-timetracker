package com.sbishyr.timetracker.persistence;

import com.sbishyr.timetracker.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserService extends CrudRepository<User, Long> {
}
