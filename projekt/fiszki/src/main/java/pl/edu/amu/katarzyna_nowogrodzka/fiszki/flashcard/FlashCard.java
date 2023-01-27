package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Getter
@Setter
@JacksonXmlRootElement(localName = "FlashCard")
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
        @JacksonXmlProperty(isAttribute = true)
        private Long id;
        @JacksonXmlProperty
        private String word;

        @JacksonXmlProperty
        private String translation;
        @JacksonXmlProperty
        private LocalDate nextReview;
        @JacksonXmlProperty
        private Integer level;
        public FlashCard() {

        }
        public FlashCard(String word, String translation) {
            this.word = word;
            this.translation = translation;
            this.level = 0;
        }
}
