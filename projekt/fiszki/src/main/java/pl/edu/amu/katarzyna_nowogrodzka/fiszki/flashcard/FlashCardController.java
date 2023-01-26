package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="api/flashcards")
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

    @PostMapping
    public void registerFlashCard(@RequestBody FlashCard flashCard) {
        flashCardService.addNewFlashCard(flashCard);
    }

    @DeleteMapping("{flashCardId}")
    public void deleteFlashCard(@PathVariable("flashCardId") Long flashCardId) {
        System.out.println("Helo");
        flashCardService.deleteFlashCard(flashCardId);
    }

    @PutMapping("{flashCardId}")
    public void updateFlashCard(
            @PathVariable("flashCardId") Long flashCardId,
            @RequestParam(required = false) String word,
            @RequestParam(required = false) String translation) {
        flashCardService.updateFlashCard(flashCardId, word, translation);
    }
}
