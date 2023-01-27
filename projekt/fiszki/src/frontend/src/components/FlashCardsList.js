import React, {useState, useEffect} from 'react'
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';

function FlashCardsList() {
    const [flashcards, setFlashCards] = useState([]);
    const [my_list, setMylist] = useState([])


    const getFlashCards = () => {
        fetch("http://localhost:8080/api/flashcards").then(res => {
            res.json().then(data => {
                setFlashCards(data)
            })
        })
    }

    useEffect(() => {
        getFlashCards();
    }, [])

    useEffect(() => {
        createList();
    }, [flashcards])    

    const createList = () => {
        let list = [];
        flashcards.forEach(flashcard => {
          const {word, translation} = flashcard || ''
            list.push(
              <>
                <ListItem >
                  <ListItemText
                    primary={word}
                    secondary={
                    <React.Fragment>
                    <Typography
                      sx={{ display: 'inline' }}
                      component="span"
                      variant="body2"
                      color="text.primary"
                    >
                    </Typography>
                    {translation}
                    </React.Fragment>
                    }
                  />
                </ListItem>
                <Divider variant="inset" component="li" />
              </>
            )
        })
        setMylist(list)
    }

    if (my_list) {
      return (
        <List id="flashcard-list" sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {my_list}
      </List>
    );
    }
}


export default FlashCardsList