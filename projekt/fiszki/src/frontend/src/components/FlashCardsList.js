import React, {useState, useEffect} from 'react'
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

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

    const generateXML = () => {
      fetch("http://localhost:8080/api/flashcards/generateXML/", {
        method: "GET",
        headers: {
          "Content-Type": "application/xml"
        }
      }).then(res =>res.text().then(data => downloadFile(data))
      )
    }

    const downloadFile = (data) => {
      const element = document.createElement("a");
      const file = new Blob([data],    
               {type: 'application/xml;charset=utf-8'});
      element.href = URL.createObjectURL(file);
      element.download = "flashCards.xml";
      document.body.appendChild(element);
      element.click();
      }

    if (my_list) {
      return (
        <List id="flashcard-list" sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
          <Button variant="outlined" onClick={() => generateXML()}>Pobierz listę fiszek XML</Button>
        {my_list}
      </List>
    );
    }
}


export default FlashCardsList