package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM FlashCard s " +
            "WHERE s.word = ?1"
    )
    Boolean findFlashCardByWord(String word);
}
