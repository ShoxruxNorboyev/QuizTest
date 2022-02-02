package bestteam.quiztest.model;

import bestteam.quiztest.model.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Questions extends AbsEntity {

    private String optionA;
    private String optionB;
    private String optionC;
    private String answer;
    private String choose;

    @Override
    public String toString() {
        return '{' +
                " Savol : " + getName() +
                " A) " + optionA + "," +
                " B) " + optionB + "," +
                " C) " + optionC + '}';
    }
}


