package com.example.ankit.advisr.handlers;

import com.example.ankit.advisr.interfaces.QuestionType;
import com.example.ankit.advisr.model.Question;
import com.example.ankit.advisr.model.User;
import com.example.ankit.advisr.repositories.QuestionRepository;
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
import static com.example.ankit.advisr.utils.IOUtils.createLikeQuery;

@AllArgsConstructor
@Slf4j
public class QuestionHandler {

    private QuestionRepository questionRepository;

    private UserRepository userRepository;

    public List<Question> getQuestions(@NonNull final long id, @NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return questionRepository.findByRequester_Id(id);
        }
        return null;
    }

    public Question askQuestion(@NonNull final Question question, @NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {

            final Timestamp timestamp = Timestamp.from(Instant.now());
            question.setTimestamp(timestamp);

            final List<User> users = new ArrayList<>();
            if (question.getPreferredUser() != null) {
                question.getPreferredUser().forEach(user -> {
                    log.info("user found");
                    log.info(userRepository.findById(user.getId()) + "");
                    users.add(userRepository.findById(user.getId()));
                });
            }

            question.setPreferredUser(users);

            question.setRequester(userRepository.findById((long) request.getSession().getAttribute(USER_ID)));

            questionRepository.save(question);
            return questionRepository.findByTimestampAndRequester_Id(timestamp, (long)request.getSession().getAttribute(USER_ID));
        }
        return null;
    }

    public List<Question> getPriorityQuestions(@NonNull final HttpServletRequest request) {
        if (request.getSession() != null && request.getSession().getAttribute(USER_ID) != null) {
            return questionRepository.findAllByType(QuestionType.PRIORITY);
        }
        return null;
    }

    public List<Question> getQueByHeadingAndDescription(@NonNull final Question question) {
        return questionRepository.findByDescriptionLikeOrHeadingIsLike(createLikeQuery(question.getDescription()), createLikeQuery(question.getHeading()));
    }

    public Question getQueById(@RequestBody @NonNull final long questionId){
        return questionRepository.findById(questionId);
    }

}
