import React from 'react'

function NoFlashCardsInfo() {
  return (
    <div id="noflashcards-info">
        <div>
            <p>Brawo!</p>
            <p>Zrobiłeś wszystkie fiszki na dziś!</p>
            <p>Wróć do nas jutro!</p>
        </div>
        <img src={require("../images/trophy.jpg")} alt="" />
    </div>
  )
}

export default NoFlashCardsInfo