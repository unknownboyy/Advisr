package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findById(long id);

}
