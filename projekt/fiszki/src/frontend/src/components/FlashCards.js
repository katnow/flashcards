import React, {useState, useEffect} from 'react';
import FlashCard from './FlashCard';

function FlashCards() {
    const [flashCards, setFlashCards] = useState([]);
    const [currentFlashCard, setCurrentFlashCard] = useState(1);

    const getFlashCardsToReview = () => {
        fetch("http://localhost:8080/api/flashcards/review/").then(res => {
            res.json().then(data => {
                setFlashCards(data)
            })
        })
    }

    useEffect(() => {
        getFlashCardsToReview();
    },[])
    
    const checkFlashCardAvailability = (index) => {
      if (flashCards[currentFlashCard - 1]) {
        return flashCards[currentFlashCard - 1]
      } else {
        setCurrentFlashCard(1)
        return flashCards[0];
      }
    }

    if (flashCards.length > 0) {
      return (
        <FlashCard flashcard={checkFlashCardAvailability(currentFlashCard)} setCurrentFlashCard={setCurrentFlashCard} getFlashCardsToReview={getFlashCardsToReview}/>
      ) 
    } else {}
}

export default FlashCards