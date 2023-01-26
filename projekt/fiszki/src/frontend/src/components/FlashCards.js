import React, {useState, useEffect} from 'react';
import FlashCard from './FlashCard';

function FlashCards() {
    const [flashCards, setFlashCards] = useState([]);
    const [currentFlashCard, setCurrentFlashCard] = useState(1);

    useEffect(() => {
        fetch("http://localhost:8080/api/flashcards").then(res => {
            res.json().then(data => {
                setFlashCards(data)
            })
        })
    },[])

    console.log(flashCards)
    
  return (
    <FlashCard flashcard={flashCards[currentFlashCard-1]} setCurrentFlashCard={setCurrentFlashCard}/>
  )
}

export default FlashCards