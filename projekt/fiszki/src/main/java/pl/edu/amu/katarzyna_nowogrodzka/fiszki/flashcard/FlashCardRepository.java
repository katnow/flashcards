package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlashCardRepository extends JpaRepository<FlashCard, Long> {

}
