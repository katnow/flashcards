package pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.scope.ScopedProxyUtils;
import pl.edu.amu.katarzyna_nowogrodzka.fiszki.flashcard.exception.BadRequestException;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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

    @Test
    void canAddFlashCard() {
        FlashCard flashCard = new FlashCard("kot", "cat");
        underTest.addNewFlashCard(flashCard);

        ArgumentCaptor<FlashCard> flashCardArgumentCaptor = ArgumentCaptor.forClass(FlashCard.class);
        verify(flashCardRepository).save(flashCardArgumentCaptor.capture());

        FlashCard capturedFlashCard = flashCardArgumentCaptor.getValue();
        assertTrue(capturedFlashCard.equals(flashCard));
    }

    @Test
    void willThrowWhenWordAlreadyExists() {
        FlashCard flashCard = new FlashCard("kot", "cat");

        given(flashCardRepository.findFlashCardByWord(anyString()))
                .willReturn(true);

        assertThatThrownBy(() -> underTest.addNewFlashCard(flashCard))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("word exists");

        verify(flashCardRepository, never()).save(any());

    }
}
