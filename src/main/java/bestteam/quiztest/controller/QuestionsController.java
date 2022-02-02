package bestteam.quiztest.controller;

import bestteam.quiztest.dto.ApiResponse;
import bestteam.quiztest.dto.QuestionsDto;
import bestteam.quiztest.service.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionsController {

    @Autowired
    QuestionsService questionsService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<?> add(@RequestBody QuestionsDto questionsDto){
        ApiResponse apiResponse = questionsService.add(questionsDto);
        return ResponseEntity.ok(apiResponse);
    }
}
