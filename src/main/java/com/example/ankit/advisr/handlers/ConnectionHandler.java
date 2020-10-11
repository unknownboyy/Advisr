package com.example.ankit.advisr.handlers;

import com.example.ankit.advisr.interfaces.ConnectionType;
import com.example.ankit.advisr.model.Connection;
import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.repositories.ConnectionRepository;
import com.example.ankit.advisr.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static com.example.ankit.advisr.utils.Constants.Session.USER_ID;
import static com.example.ankit.advisr.utils.IOUtils.convertToUserList;

@Slf4j
@AllArgsConstructor
public class ConnectionHandler {

    private ConnectionRepository connectionRepository;
    private UserRepository userRepository;

    public List<Connection> getConnectionRequests(@NonNull final HttpServletRequest request) {
        final long id = (long) request.getSession().getAttribute(USER_ID);
        return connectionRepository.findByTo_IdAndType(id, ConnectionType.REQUEST);
    }

    public List<Connection> getConnections(@NonNull final HttpServletRequest request) {
        final long id = (long) request.getSession().getAttribute(USER_ID);
        List <Connection> connections = new ArrayList<>();
        connections.addAll(connectionRepository.findByFrom_IdAndType(id, ConnectionType.ACTIVE));
        connections.addAll(connectionRepository.findByTo_IdAndType(id, ConnectionType.ACTIVE));
        return connections;
    }

    public List<User> getSuggestions(@NonNull final HttpServletRequest request) {
        final long id = (long) request.getSession().getAttribute(USER_ID);
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return convertToUserList(connectionRepository.findSuggestions(id, id, id, id));
        }
        return null;
    }

    public boolean requestConnection(@RequestBody @NonNull final long userId, @NonNull final HttpServletRequest request) {
        final long id = (long) request.getSession().getAttribute(USER_ID);
        final Connection connection = Connection.builder()
                .from(userRepository.findById(id))
                .timestamp(Timestamp.from(Instant.now()))
                .to(userRepository.findById(userId))
                .type(ConnectionType.REQUEST)
                .build();
        connectionRepository.save(connection);
        return true;
    }

    public List<Connection> getMyRequests(@NonNull final HttpServletRequest request) {
        final long id = (long) request.getSession().getAttribute(USER_ID);
        return connectionRepository.findByFrom_IdAndType(id, ConnectionType.REQUEST);
    }

    public Connection acceptConnection(@NonNull final Connection connection, @NonNull final HttpServletRequest request) {
        final Connection updatedConnection = connectionRepository.findById(connection.getId());
        updatedConnection.setType(ConnectionType.ACTIVE);
        connectionRepository.save(updatedConnection);
        return connectionRepository.findById(connection.getId());
    }

}
