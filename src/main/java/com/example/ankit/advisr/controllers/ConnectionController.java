package com.example.ankit.advisr.controllers;

import com.example.ankit.advisr.handlers.ConnectionHandler;
import com.example.ankit.advisr.model.Connection;
import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.utils.Constants;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static com.example.ankit.advisr.utils.Constants.APPLICATION_JSON;
import static com.example.ankit.advisr.utils.Constants.Session.USER_ID;

@RestController
@RequestMapping("/api")
public class ConnectionController {

    @Autowired
    private ConnectionHandler connectionHandler;

    @RequestMapping(value = Constants.Routes.GET_CONNECTION_REQUESTS, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<Connection> getConnectionRequests(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return connectionHandler.getConnectionRequests(request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.GET_CONNECTIONS, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<Connection> getConnections(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return connectionHandler.getConnections(request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.GET_SUGGESTIONS, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<User> getSuggestions(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return connectionHandler.getSuggestions(request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.REQUEST_CONNECTION, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public boolean requestConnection(@RequestBody @NonNull final long userId, @NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return connectionHandler.requestConnection(userId, request);
        }
        return false;
    }

    @RequestMapping(value = Constants.Routes.GET_MY_REQUESTS, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<Connection> getMyRequests(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return connectionHandler.getMyRequests(request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.ACCEPT_CONNECTION, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public Connection acceptConnection(@RequestBody @NonNull final Connection connection, @NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return connectionHandler.acceptConnection(connection, request);
        }
        return null;
    }


}
