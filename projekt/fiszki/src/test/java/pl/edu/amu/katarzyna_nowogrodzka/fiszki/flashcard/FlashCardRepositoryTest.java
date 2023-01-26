package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class FlashCardRepositoryTest {
    @Autowired
    private FlashCardRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void checkFindingFlashCardByWord() {
        String word = "kot";
        FlashCard flashCard = new FlashCard(word, "cat");

        underTest.save(flashCard);
        Optional<FlashCard> expected = underTest.findFlashCardByWord(word);

        assertTrue((expected.get().getWord()).equals("kot"));
    }
}
