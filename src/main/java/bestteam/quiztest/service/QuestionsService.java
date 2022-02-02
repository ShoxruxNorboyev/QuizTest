package bestteam.quiztest.service;

import bestteam.quiztest.dto.ApiResponse;
import bestteam.quiztest.dto.QuestionsDto;
import bestteam.quiztest.model.Questions;
import bestteam.quiztest.repository.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionsService {

    @Autowired
    QuestionsRepository questionsRepository;

    public ApiResponse add(QuestionsDto questionsDto){
        Questions questions = new Questions();

        if (!questionsRepository.existsByName(questionsDto.getName())){

            questions.setName(questionsDto.getName());
            questions.setOptionA(questionsDto.getOptionA());
            questions.setOptionB(questionsDto.getOptionB());
            questions.setOptionC(questionsDto.getOptionC());
            questions.setAnswer(questionsDto.getAnswer());


            questionsRepository.save(questions);
            return new ApiResponse("Saved",true,questions.getName());
        }

        return new ApiResponse("This question already exists",false);
    }

    public List<Questions> getAll(){

        List<Questions> byId = questionsRepository.findAll();
        return byId;
    }

    public Questions getOneById(Integer id){
        Optional<Questions> byId = questionsRepository.findById(id);
        return byId.orElse(null);
    }

    public Questions edit(Integer id, QuestionsDto questionsDto){
        Optional<Questions> byId = questionsRepository.findById(id);

        if (byId.isPresent()){
            Questions editQuestion = byId.get();
            editQuestion.setName(questionsDto.getName());
            editQuestion.setAnswer(questionsDto.getAnswer());
            editQuestion.setOptionA(questionsDto.getOptionA());
            editQuestion.setOptionB(questionsDto.getOptionB());
            editQuestion.setOptionC(questionsDto.getOptionC());

            return questionsRepository.save(editQuestion);
        }

        return null;
    }

    public Questions delete(Integer id, QuestionsDto questionsDto){
        Optional<Questions> byId = questionsRepository.findById(id);

        if(byId.isPresent()){
            Questions deleteQuestion = byId.get();
            deleteQuestion.setActive(questionsDto.isActive());

            return questionsRepository.save(deleteQuestion);
        }
        return null;
    }
}
