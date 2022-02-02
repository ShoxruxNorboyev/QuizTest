package bestteam.quiztest.controller;

import bestteam.quiztest.dto.ApiResponse;
import bestteam.quiztest.model.AppUser;
import bestteam.quiztest.model.QuestionForm;
import bestteam.quiztest.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/api/quiz")
public class QuizController {

    @Autowired(required = false)
    AppUser appUser;
    @Autowired
    QuizService quizService;
    Boolean submitted = false;

    @PostMapping("/start")
    public ApiResponse start(@RequestBody AppUser appUser) {
        if (appUser.equals("")) {
            return new ApiResponse("Username is empty", false);
        }
        submitted = false;
        appUser.setUsername(appUser.getUsername());

        String questionForm = quizService.getQuestions().toString();

        return new ApiResponse("Quiz", true,questionForm);
    }

    @PostMapping("/submit")
    public ApiResponse submit(@RequestParam QuestionForm questionForm){
        if (!submitted){
            appUser.setTotalCorrect(quizService.getResult(questionForm));
            quizService.saveScore(appUser);
            submitted = true;
        }
        return new ApiResponse("Result", true,
                "AppUser Name -> " + appUser.getUsername() + " "
                        + "TotalCorrect -> " + appUser.getTotalCorrect());
    }

    @GetMapping("/score")
    public ApiResponse score(){
        List<AppUser> appUserList = quizService.getTopScore();
        return new ApiResponse("Top Score",true,appUserList);
    }
}
