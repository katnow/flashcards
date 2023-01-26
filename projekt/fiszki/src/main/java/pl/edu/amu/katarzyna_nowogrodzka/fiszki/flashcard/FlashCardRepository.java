package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {
    @Query("SELECT f FROM FlashCard f WHERE f.word = ?1")
    Optional<FlashCard> findFlashCardByWord(String word);
}
