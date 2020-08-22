package com.example.ankit.advisr.controllers;

import com.example.ankit.advisr.handlers.UserHandler;
import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.utils.Constants;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserHandler userHandler;

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    @RequestMapping(value = Constants.Routes.LOGIN, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public User login(@RequestBody @NonNull User user, @NonNull final HttpServletRequest request) {

        log.info("login");
        log.info(user.getEmail());
        log.info(user.getPassword());

        return userHandler.loginUser(user, request);

    }

    @RequestMapping(value = Constants.Routes.SIGN_UP, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public User signUp(@RequestBody @NonNull User user,
                      @NonNull final HttpServletRequest request) {

        log.info("signUp");
        log.info(user + "");
        user.setTimestamp(Timestamp.from(Instant.now()));
        return userHandler.signUpUser(user, request);

    }

    @RequestMapping(value = Constants.Routes.LOGOUT, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public void logout(@NonNull final HttpServletRequest request) {
        log.info("logout");
        request.getSession().invalidate();
    }

    @RequestMapping(value = Constants.Routes.GET_CURRENT_USER, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public User getCurrentUser(@NonNull final HttpServletRequest request) {
        return userHandler.getCurrentUser(request);
    }



}
