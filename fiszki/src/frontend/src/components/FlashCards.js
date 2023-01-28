import React, {useState, useEffect} from 'react';
import FlashCard from './FlashCard';
import NoFlashCardsInfo from './NoFlashCardsInfo';

function FlashCards({mode}) {
    const [flashCards, setFlashCards] = useState([]);
    const [currentFlashCard, setCurrentFlashCard] = useState(1);

    const getFlashCards = () => {
      if (mode === "review") {
        fetch("http://localhost:8080/api/flashcards/review/").then(res => {
            res.json().then(data => {
                setFlashCards(data)
            })
        })
      } else if (mode === "learn") {
        fetch("http://localhost:8080/api/flashcards/learn/").then(res => {
            res.json().then(data => {
                setFlashCards(data)
            })
        })
      }
    }

    useEffect(() => {
        getFlashCards();
    },[mode])
    
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
        <FlashCard flashcard={checkFlashCardAvailability(currentFlashCard)} setCurrentFlashCard={setCurrentFlashCard} getFlashCardsToReview={getFlashCards}/>
      ) 
    } else {
      return (
        <NoFlashCardsInfo />
      )
    }
}

export default FlashCards