package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Getter
@Setter
public class FlashCard {
    @Id
    @SequenceGenerator(
            name = "flashcard_sequence",
                sequenceName = "flashcard_sequence",
                allocationSize = 1
        )
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "flashcard_sequence"
        )
        private Long id;

        private String word;

        private String translation;
        private LocalDate lastReview;

        private Integer level;
        public FlashCard() {
        }

    public FlashCard(String word, String translation, LocalDate lastReview, Integer level) {
        this.word = word;
        this.translation = translation;
        this.lastReview = lastReview;
        this.level = level;
    }
}