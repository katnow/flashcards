package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class FlashCardServiceTest {

    @Mock
    private FlashCardRepository flashCardRepository;
    private FlashCardService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FlashCardService(flashCardRepository);
    }

    @Test
    void canGetAllFlashCards() {
        underTest.getFlashCards();
        verify(flashCardRepository).findAll();
    }

}
