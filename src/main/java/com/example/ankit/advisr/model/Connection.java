package com.example.ankit.advisr.model;

import com.example.ankit.advisr.interfaces.ConnectionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.sql.Timestamp;

@Data
@Entity
@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Timestamp timestamp;

    private ConnectionType type;

    @OneToOne
    private User from;

    @OneToOne
    private User to;

}
