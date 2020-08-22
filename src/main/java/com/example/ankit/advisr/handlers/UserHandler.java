package com.example.ankit.advisr.handlers;

import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.repositories.UserRepository;
import com.example.ankit.advisr.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Slf4j
public class UserHandler {

    private UserRepository userRepository;

    public User loginUser(@NonNull final User user, @NonNull final HttpServletRequest request) {

        final User dbUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        log.info("loginUserHandler");
        if (dbUser != null) {
            request.getSession().setAttribute(Constants.Session.USER_ID, dbUser.getId());
        }
        return dbUser;

    }

    public User signUpUser(@NonNull final User user, @NonNull final HttpServletRequest request) {

        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser == null) {
            userRepository.save(user);
            dbUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
            request.getSession().setAttribute(Constants.Session.USER_ID, dbUser.getId());
            return dbUser;
        }

        return null;

    }

    public User getCurrentUser(@NonNull final HttpServletRequest request) {
        final HttpSession session =  request.getSession();
        if (session != null && request.getSession().getAttribute(Constants.Session.USER_ID) != null) {
            final long userId = (long) request.getSession().getAttribute(Constants.Session.USER_ID);
            return userRepository.findById(userId);
        }
        return null;
    }

}
