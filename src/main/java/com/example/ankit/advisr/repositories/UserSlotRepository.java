package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.model.UserSlot;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserSlotRepository extends CrudRepository<UserSlot, Long> {

    UserSlot findById(long id);

    List<UserSlot> findByUser_Id(long id);

}
