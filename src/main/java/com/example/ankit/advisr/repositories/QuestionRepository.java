package com.example.ankit.advisr.repositories;

import com.example.ankit.advisr.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByDescriptionLikeOrHeadingIsLike(String description, String heading);

    Question findById(long id);

    List<Question> findByRequester_Id(long id);

}
