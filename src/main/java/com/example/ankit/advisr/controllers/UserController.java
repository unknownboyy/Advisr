package com.example.ankit.advisr.controllers;

import com.example.ankit.advisr.handlers.UserHandler;
import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.model.UserDetails;
import com.example.ankit.advisr.requestmodel.SignUpUserRequest;
import com.example.ankit.advisr.utils.Constants;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.example.ankit.advisr.utils.Constants.APPLICATION_JSON;


@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserHandler userHandler;

    @RequestMapping(value = Constants.Routes.LOGIN, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public UserDetails login(@RequestBody @NonNull SignUpUserRequest user, @NonNull final HttpServletRequest request) {

        return userHandler.loginUser(user, request);

    }

    @RequestMapping(value = Constants.Routes.SIGN_UP, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public UserDetails signUp(@RequestBody @NonNull SignUpUserRequest user,
                      @NonNull final HttpServletRequest request) {

        return userHandler.signUpUser(user, request);

    }

    @RequestMapping(value = Constants.Routes.LOGOUT, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public void logout(@NonNull final HttpServletRequest request) {
        request.getSession().invalidate();
    }

    @RequestMapping(value = Constants.Routes.GET_CURRENT_USER, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public User getCurrentUser(@NonNull final HttpServletRequest request) {
        return userHandler.getCurrentUser(request);
    }



}
