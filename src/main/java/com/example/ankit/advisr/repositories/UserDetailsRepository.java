package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.model.UserDetails;
import org.springframework.data.repository.CrudRepository;

public interface UserDetailsRepository extends CrudRepository<UserDetails, Long> {

    UserDetails findById(long id);

    UserDetails findByUser_Email(String email);

    UserDetails findByUser_EmailAndPassword(String email, String password);

    UserDetails findByUser_FirstNameOrUser_LastName(String firstName, String lastName);

}
