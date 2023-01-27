package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard.exception.BadRequestException;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlashCardService {
    private final FlashCardRepository flashCardRepository;

    public FlashCardService(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    public List<FlashCard> getFlashCards() {
        return flashCardRepository.findAll();
    }

    public List<FlashCard> getFlashCardsToReview() {
        LocalDate now = LocalDate.now();
        if (flashCardRepository.count() == 0) {
            throw new RuntimeException("Nie ma żadnych fiszek do powtórzenia");
        } else {
            return flashCardRepository.findAll().stream()
                    .filter(flashCard -> checkDatesDifference(flashCard.getNextReview() != null ? flashCard.getNextReview() : now, now) >= 0)
                    .collect(Collectors.toList());
        }
    }

    public List<FlashCard> getNewFlashCards() {
        if (flashCardRepository.count() == 0) {
            throw new RuntimeException("Brak fiszek");
        } else {
            return flashCardRepository.findAll().stream()
                    .filter(flashCard -> flashCard.getLevel() == 0)
                    .collect(Collectors.toList());
        }
    }

    public int checkDatesDifference(LocalDate date1, LocalDate date2) {
        Period period = Period.between(date1, date2);
        int days = period.getDays();
        return days;
    }

    public void addNewFlashCard(FlashCard flashCard) {
        Boolean existsWord = flashCardRepository.findFlashCardByWord(flashCard.getWord());

        if (existsWord) {
            throw new BadRequestException("word exists");
        }
            flashCard.setLevel(0);
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
            Boolean flashCardExists = flashCardRepository.findFlashCardByWord(word);

            flashCard.setWord(word);
        }

        if (translation != null && translation.length() > 0) {
            flashCard.setTranslation(translation);
        }
    }

    @Transactional
    public void changeFlashCardLevel(Long flashCardId, Integer option) {
        int[] levelsInDays = new int[] {0, 1, 2, 3, 5, 9, 14, 21, 28};
        FlashCard flashCard = flashCardRepository.findById(flashCardId)
                .orElseThrow(() -> new IllegalStateException(
                        "flashcard with id " + flashCardId + " does not exist"
                ));
        if (option == 0) {
            flashCard.setLevel(0);
        } else if (option == 2 && flashCard.getLevel() != 8) {
            int currentLevel = flashCard.getLevel();
            flashCard.setLevel(currentLevel + 1);
        }


        int currentLevel = flashCard.getLevel();

        int daysCount = levelsInDays[currentLevel];

        LocalDate today = LocalDate.now();
        LocalDate nextReview = today.plusDays(daysCount);

        if (option == 2 || option == 1) {
            flashCard.setNextReview(nextReview);
        } else {
            flashCard.setNextReview(today);
        }
    }
}
