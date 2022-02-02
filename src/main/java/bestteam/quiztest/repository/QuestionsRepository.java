package bestteam.quiztest.repository;

import bestteam.quiztest.model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository  extends JpaRepository<Questions, Integer> {
    boolean existsByName(String name);
}
