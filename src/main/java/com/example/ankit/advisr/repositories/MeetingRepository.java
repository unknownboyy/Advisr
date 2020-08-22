package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.model.Meeting;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {

    Meeting findById(long id);

    List<Meeting> findByRequester_Id(long id);

    List<Meeting> findByAdvisor_Id(long id);

}
