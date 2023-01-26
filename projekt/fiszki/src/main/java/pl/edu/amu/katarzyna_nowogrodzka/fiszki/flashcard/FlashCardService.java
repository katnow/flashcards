package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlashCardService {
    private final FlashCardRepository flashCardRepository;

    public FlashCardService(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    public List<FlashCard> getFlashCards() {
        return flashCardRepository.findAll();
    }
}
