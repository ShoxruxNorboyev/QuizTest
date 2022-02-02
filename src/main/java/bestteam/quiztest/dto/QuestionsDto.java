package bestteam.quiztest.dto;

import lombok.Data;

@Data
public class QuestionsDto {
    private Integer id;
    private String name;
    private String optionA;
    private String optionB;
    private String optionC;
    private String answer;
    private boolean active;
}
