package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FlashCardService {
    private final FlashCardRepository flashCardRepository;

    public FlashCardService(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    public List<FlashCard> getFlashCards() {
        return flashCardRepository.findAll();
    }

    public void addNewFlashCard(FlashCard flashCard) {
        Optional<FlashCard> flashCardOptional = flashCardRepository.findFlashCardByWord(flashCard.getWord());

        if (flashCardOptional.isPresent()) {
                throw new IllegalStateException("word exists");
        }
            flashCardRepository.save(flashCard);
    }

    public void deleteFlashCard(Long flashCardId) {
        boolean exists = flashCardRepository.existsById(flashCardId);

        if (!exists) {
            throw new IllegalStateException("flashcard with id " + flashCardId + " does not exist");
        }

        flashCardRepository.deleteById(flashCardId);
    }

    @Transactional
    public void updateFlashCard(Long flashCardId,
                                String word,
                                String translation) {
        FlashCard flashCard = flashCardRepository.findById(flashCardId)
                .orElseThrow(() -> new IllegalStateException(
                        "flashcard with id " + flashCardId + " does not exist"
                 ));

        if (word != null && word.length() > 0 && !Objects.equals(flashCard.getWord(), word)) {
            Optional<FlashCard> flashCardOptional = flashCardRepository.findFlashCardByWord(word);

            flashCard.setWord(word);
        }

        if (translation != null && translation.length() > 0) {
            flashCard.setTranslation(translation);
        }
    }
}
