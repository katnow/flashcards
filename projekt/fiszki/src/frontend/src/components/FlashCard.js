import React, {useState} from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';

export default function ImgMediaCard({flashcard, setCurrentFlashCard}) {
   const {id, word, translation} = flashcard || ''
   const [hidden, setHidden] = useState(true);
   const buttons = [
    {color: "error", text: "Nie wiem", value: 0},
    {color: "primary", text: "Mniej więcej", value: 1},
    {color: "success", text: "Wiem", value: 2}
   ]

    const updateLevel = (value) => {
        fetch(`http://localhost:8080/api/flashcards/${id}/${value}`, {
            method: 'PUT',

        }).then(res => {
            if (res.ok) {
                setCurrentFlashCard(prev => prev + 1)
                setHidden(true)
            }
        })
    }
   
  return (
    <Card className="flashcard" sx={{ maxWidth: 345 }} onClick={() => setHidden(false)}>
      <CardContent>
        <Typography className='word title' gutterBottom variant="h5" component="div" style={{opacity: 1}}>
          {word}
        </Typography>
        <Divider/>
        <Typography style={hidden ? {opacity: "0"} : {}} className='word' gutterBottom variant="h5" component="div">
          {translation}
        </Typography>
      </CardContent>
      <CardActions class="buttons">
        {buttons.map(button => {
            const {color, text, value} = button || ''
            return(  
                <Button className="button" size="small" color={color} value={value} onClick={e => updateLevel(e.target.value)}>
                    {text}
                </Button>
            )
        })}
      </CardActions>
    </Card>
  );
}