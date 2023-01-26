import React, {useState} from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';

export default function ImgMediaCard({flashcard}) {
   const {word, translation} = flashcard || ''
   const [hidden, setHidden] = useState(true);
  return (
    <Card className="flashcard" sx={{ maxWidth: 345 }} onClick={() => setHidden(false)}>
      <CardContent>
        <Typography className='word' gutterBottom variant="h5" component="div">
          {word}
        </Typography>
        <Divider/>
        <Typography style={hidden ? {visibility: "hidden"} : {}} className='word' gutterBottom variant="h5" component="div">
          {translation}
        </Typography>
      </CardContent>
      <CardActions class="buttons">
        <Button className="button" size="small" color="error">Nie wiem</Button>
        <Button className="button" size="small" color="primary">Mniej więcej</Button>
        <Button className="button" size="small" color="success">Wiem</Button>
      </CardActions>
    </Card>
  );
}