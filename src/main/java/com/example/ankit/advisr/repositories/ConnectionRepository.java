package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.interfaces.ConnectionType;
import com.example.ankit.advisr.model.Connection;
import com.example.ankit.advisr.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConnectionRepository extends CrudRepository<Connection, Long> {

    void deleteById(long id);

    List<Connection> findByFrom_IdOrTo_Id(long fromId, long toId);

    List<Connection> findByTo_IdAndType(long id, ConnectionType type);

    List<Connection> findByFrom_IdAndType(long id, ConnectionType type);

    Connection findById(long id);

    @Query(nativeQuery = true, value = "select u.id, u.first_name, u.last_name, u.email from connection c1 left join connection c2 on c1.to_id=c2.from_id or c1.from_id=c2.from_id left join user u on c2.to_id=u.id where c1.from_id = ? and u.id is not null and u.id != ? union all select u.id, u.first_name, u.last_name, u.email from connection c1 left join connection c2 on c1.to_id=c2.to_id or c1.from_id=c2.to_id left join user u on c2.from_id=u.id where c1.from_id = ? and u.id is not null and u.id != ?")
    List<List<Object>> findSuggestions(long id1, long id2, long id3, long id4);

}
