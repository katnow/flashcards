import React, {useState, useEffect} from 'react'
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import Divider from '@mui/material/Divider';
import ListItemText from '@mui/material/ListItemText';
import ListItemAvatar from '@mui/material/ListItemAvatar';
import Avatar from '@mui/material/Avatar';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import DeleteIcon from '@mui/icons-material/Delete';
import ModeEditIcon from '@mui/icons-material/ModeEdit';
import TextField from '@mui/material/TextField';
import SaveIcon from '@mui/icons-material/Save';

function FlashCardsList() {
    const [flashcards, setFlashCards] = useState([]);
    const [my_list, setMylist] = useState([])
    const [editedFlashCard, setEditedFlashCard] = useState(null);
    const [editedTranslation, setEditedTranslation] = useState('');
    const [sending, setSending] = useState(false);

    let myTranslation = ''
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
        editFlashCard(editedTranslation)
    }, [sending])

    useEffect(() => {
        createList();
    }, [flashcards, editedFlashCard]) 

    useEffect(() => {
        console.log(editedTranslation)
        myTranslation = editedTranslation
    }, [editedTranslation])
    
    const deleteFlashCard = (id) => {
      fetch(`http://localhost:8080/api/flashcards/${id}`, {
        method: "DELETE"
      }).then(res => {
        if (res.ok) {
          getFlashCards();
        }
      })
    }

    const editFlashCard = (translation) => {
      setSending(false)
      if (translation && editedFlashCard) {
      fetch(`http://localhost:8080/api/flashcards/${editedFlashCard}?translation=${translation}`, {
        method: "PUT"
      }).then(res => {
        if (res.ok) {
          setEditedFlashCard(null);
          setEditedTranslation('');
          getFlashCards();
        }
      })
    }
    }


    const createList = () => {
        let list = [];
        flashcards.forEach(flashcard => {
          const {id, word, translation} = flashcard || ''
            list.push(
              <>
                <ListItem value={id}>
                  {editedFlashCard !== id ?
                  <ListItemText
                    primary={word}
                    secondary={
                    <React.Fragment>
                      {translation}
                    </React.Fragment>
                    }
                  /> :
                  <>
                  <ListItemText
                    primary={word}
                  />
                  <TextField id="outlined-basic" label="Tłumaczenie" variant="outlined" onChange={(e) => setEditedTranslation(e.target.value)}/>
                  </> }
                  <DeleteIcon onClick={(e)=> deleteFlashCard(e.target.parentNode.parentNode.value)} className="icon"/>
                  {editedFlashCard !== id ?
                  <ModeEditIcon onClick={(e) => setEditedFlashCard(e.target.parentNode.parentNode.value)} className="icon"/>
                  : 
                  <SaveIcon className="icon" onClick={(e)=> {setSending(true)}}/>}
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
        <>
        <Button id="download-xml-btn" variant="outlined" onClick={() => generateXML()}>Pobierz listę fiszek XML</Button>
        <List id="flashcard-list" sx={{ width: '100%', maxWidth: 360, bgcolor: 'background.paper' }}>
        {my_list}
      </List>
      </>
    );
    }
}


export default FlashCardsList