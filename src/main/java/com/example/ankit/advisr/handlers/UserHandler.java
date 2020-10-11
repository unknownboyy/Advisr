package com.example.ankit.advisr.handlers;

import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.model.UserDetails;
import com.example.ankit.advisr.repositories.UserDetailsRepository;
import com.example.ankit.advisr.repositories.UserRepository;
import com.example.ankit.advisr.requestmodel.SignUpUserRequest;
import com.example.ankit.advisr.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;

@AllArgsConstructor
@Slf4j
public class UserHandler {

    private UserDetailsRepository userDetailsRepository;

    private UserRepository userRepository;

    public UserDetails loginUser(@NonNull final SignUpUserRequest user, @NonNull final HttpServletRequest request) {

        final UserDetails dbUser = userDetailsRepository.findByUser_EmailAndPassword(user.getEmail(), user.getPassword());
        log.info("loginUserHandler");
        if (dbUser != null) {
            request.getSession().setAttribute(Constants.Session.USER_ID, dbUser.getUser().getId());
        }
        return dbUser;

    }

    public UserDetails signUpUser(@NonNull final SignUpUserRequest user, @NonNull final HttpServletRequest request) {

        UserDetails dbUser = userDetailsRepository.findByUser_Email(user.getEmail());
        if (dbUser == null) {
            final UserDetails userDetails = UserDetails.builder()
                    .timestamp(Timestamp.from(Instant.now()))
                    .password(user.getPassword())
                    .user(
                            User.builder()
                                    .firstName(user.getFirstName())
                                    .lastName(user.getLastName())
                                    .email(user.getEmail())
                                    .build()
                    )
                    .build();
            userDetailsRepository.save(userDetails);
            dbUser = userDetailsRepository.findByUser_EmailAndPassword(user.getEmail(), user.getPassword());
            request.getSession().setAttribute(Constants.Session.USER_ID, dbUser.getUser().getId());
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
