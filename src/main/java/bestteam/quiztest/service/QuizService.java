package bestteam.quiztest.service;

import bestteam.quiztest.dto.ApiResponse;
import bestteam.quiztest.model.AppUser;
import bestteam.quiztest.model.QuestionForm;
import bestteam.quiztest.model.Questions;
import bestteam.quiztest.repository.QuestionsRepository;
import bestteam.quiztest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    @Autowired(required = false)
    Questions questions;
    @Autowired
    QuestionForm questionForm;
    @Autowired
    QuestionsRepository questionsRepository;
    @Autowired(required = false)
    AppUser appUser;
    @Autowired
    UserRepository userRepository;

    public QuestionForm getQuestions() {
        List<Questions> allQuestions = questionsRepository.findAll();

        List<Questions> quizList = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int rand = random.nextInt(allQuestions.size());
            quizList.add(allQuestions.get(rand));
            allQuestions.remove(rand);
        }

        questionForm.setQuestionsList(quizList);

        return questionForm;
    }

    public Integer getResult(QuestionForm questionForm) {
        int correct = 0;

        for (Questions questions : questionForm.getQuestionsList()) {
            if (questions.getAnswer().equals(questions.getChoose())) {
                correct++;
            }
        }
        return correct;
    }

    public ApiResponse saveScore(AppUser appUser) {
        AppUser saveResult = new AppUser();

        saveResult.setUsername(appUser.getUsername());
        saveResult.setTotalCorrect(appUser.getTotalCorrect());

        userRepository.save(saveResult);
        return new ApiResponse("Save score", true, saveResult.getTotalCorrect());
    }

    public List<AppUser> getTopScore() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));
    }

}
