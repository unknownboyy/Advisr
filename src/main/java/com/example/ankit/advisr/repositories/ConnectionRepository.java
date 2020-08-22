package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.interfaces.ConnectionType;
import com.example.ankit.advisr.model.Connection;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {

    void deleteById(long id);

    List<Connection> findByFrom_Id(long id);

    List<Connection> findByFrom_IdAndType(long id, ConnectionType type);

}
