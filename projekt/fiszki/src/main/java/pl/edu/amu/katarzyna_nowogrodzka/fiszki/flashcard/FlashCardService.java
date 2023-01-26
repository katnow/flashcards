package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.stereotype.Service;

import java.util.List;
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
}
