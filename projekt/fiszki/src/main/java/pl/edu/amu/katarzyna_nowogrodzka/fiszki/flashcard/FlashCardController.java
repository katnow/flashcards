package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="api/flashcards/")
public class FlashCardController {
    public final FlashCardService flashCardService;

    @Autowired //this.flashCardService will be instantiated for us and injected to the constructor
    public FlashCardController(FlashCardService flashCardService) {
        this.flashCardService = flashCardService;
    }

    @GetMapping
    public List<FlashCard> getFlashCards() {
        return flashCardService.getFlashCards();
    }
}
