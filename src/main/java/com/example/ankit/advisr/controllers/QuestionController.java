package com.example.ankit.advisr.controllers;

import com.example.ankit.advisr.handlers.QuestionHandler;
import com.example.ankit.advisr.interfaces.QuestionType;
import com.example.ankit.advisr.model.Question;
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
public class QuestionController {

    @Autowired
    private QuestionHandler questionHandler;

    @RequestMapping(value = Constants.Routes.GET_QUESTIONS, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<Question> getQuestions(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return questionHandler.getQuestions((long)request.getSession().getAttribute(USER_ID), request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.ASK_QUESTION, method = RequestMethod.POST, consumes = APPLICATION_JSON)
    public Question askQuestion(@RequestBody @NonNull final Question question, @NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            if (question.getType() == null) {
                question.setType(QuestionType.SIMPLE);
            }
            return questionHandler.askQuestion(question, request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.GET_PRIORITY_QUESTIONS, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<Question> getPriorityQuestions(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return questionHandler.getPriorityQuestions(request);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.GET_QUESTIONS_BY_DESCRIPTION_AND_HEADING, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public List<Question> getQueByHeadingAndDescription(@RequestBody @NonNull final Question question, @NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null){
            return questionHandler.getQueByHeadingAndDescription(question);
        }
        return null;
    }

    @RequestMapping(value = Constants.Routes.GET_QUESTION_BY_ID, method = RequestMethod.GET, consumes = APPLICATION_JSON)
    public Question getQueById(@RequestBody @NonNull final Long questionId, @NonNull final HttpServletRequest request){
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null){
            return questionHandler.getQueById(questionId);
        }
        return null;
    }

}
