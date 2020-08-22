package com.example.ankit.advisr.model;

import com.example.ankit.advisr.interfaces.MeetingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private MeetingStatus status;

    @OneToOne
    private UserSlot requester;

    @OneToOne
    private UserSlot advisor;
}
