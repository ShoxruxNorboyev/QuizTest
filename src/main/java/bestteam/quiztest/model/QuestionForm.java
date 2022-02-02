package bestteam.quiztest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class QuestionForm {

    private List<Questions> questionsList;

    @Override
    public String toString() {
        return  " " +questionsList;
    }
}
