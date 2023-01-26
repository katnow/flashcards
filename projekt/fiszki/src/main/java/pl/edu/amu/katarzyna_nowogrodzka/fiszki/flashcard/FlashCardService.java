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

//        if (flashCardOptional.isPresent()) {
//            if (flashCardOptional.get().getTranslation() == flashCard.getTranslation()) {
//                throw new IllegalStateException("word exists");
//            }
//        }
            flashCardRepository.save(flashCard);
    }
}
