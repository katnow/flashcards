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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping
    public List<FlashCard> getFlashCards() {
        return flashCardService.getFlashCards();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "review/")
    public List<FlashCard> getFlashCardsToReview() {
        return flashCardService.getFlashCardsToReview();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "learn/")
    public List<FlashCard> getNewFlashCards() {
        return flashCardService.getNewFlashCards();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public void registerFlashCard(@RequestBody FlashCard flashCard) {
        flashCardService.addNewFlashCard(flashCard);
    }

    @DeleteMapping("{flashCardId}")
    public void deleteFlashCard(@PathVariable("flashCardId") Long flashCardId) {
        flashCardService.deleteFlashCard(flashCardId);
    }

    @PutMapping("{flashCardId}")
    public void updateFlashCard(
            @PathVariable("flashCardId") Long flashCardId,
            @RequestParam(required = false) String word,
            @RequestParam(required = false) String translation) {
        flashCardService.updateFlashCard(flashCardId, word, translation);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{flashCardId}/{option}")
    public void changeFlashCardLevel(
            @PathVariable("flashCardId") Long flashCardId,
            @PathVariable("option") Integer option
    ) {
        flashCardService.changeFlashCardLevel(flashCardId, option);
    }
}
