package com.example.ankit.advisr.config;

import com.example.ankit.advisr.handlers.MeetingHandler;
import com.example.ankit.advisr.handlers.QuestionHandler;
import com.example.ankit.advisr.handlers.UserHandler;
import com.example.ankit.advisr.repositories.QuestionRepository;
import com.example.ankit.advisr.repositories.UserDetailsRepository;
import com.example.ankit.advisr.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class BeanConfig {

    @Bean
    public UserHandler getUserHandler(UserDetailsRepository userDetailsRepository, UserRepository userRepository){
        return new UserHandler(userDetailsRepository, userRepository);
    }

    @Bean
    public MeetingHandler getMeetingHandler(){
        return new MeetingHandler();
    }

    @Bean
    public QuestionHandler getQuestionHandler(QuestionRepository questionRepository, UserRepository userRepository){
        return new QuestionHandler(questionRepository, userRepository);
    }

}
