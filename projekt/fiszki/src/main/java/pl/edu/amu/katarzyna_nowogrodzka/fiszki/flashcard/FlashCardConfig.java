package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class FlashCardConfig {
    @Bean
    CommandLineRunner commandLineRunner(FlashCardRepository flashCardRepository) {
        return args -> {
            FlashCard f1 = new FlashCard("słońce", "sun", null, 0);
            FlashCard f2 = new FlashCard("wiatr", "wind", null, 0);

            flashCardRepository.saveAll(
                    List.of(f1, f2)
            );
        };
    }
}
