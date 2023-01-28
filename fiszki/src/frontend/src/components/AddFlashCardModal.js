import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import Modal from '@mui/material/Modal';
import { TextField } from '@mui/material';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 400,
  bgcolor: 'background.paper',
  border: '2px solid #000',
  boxShadow: 24,
  p: 4,
};

export default function BasicModal({open, setOpen}) {
  const handleClose = () => setOpen(false);
    console.log(open)
    let word = '';
    let translation = ''
    const handleWord = (value) => {
        console.log(value)
        word = value;
    }

    const handleTranslation = (value) => {
        translation = value
    }

    const sumbitWord = () => {
        if (word.length > 0 && translation.length > 0) {
            fetch("http://localhost:8080/api/flashcards", {
                method: 'POST',
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    word: word,
                    translation: translation
                })
            }).then(res => {
                if (res.ok) {
                  setOpen(false);
                }
            })
        }
    }

    
  return (
    <div>
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
        
      >
        <Box sx={style} id="modal">
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Dodaj fiszkę
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
     
      <div>
        <TextField
          id="standard-multiline-static"
          label="Słówko"
          multiline
          rows={1}
          defaultValue=""
          variant="standard"
          onChange={(e) => handleWord(e.target.value)}
        />
      </div>
      <div>
        <TextField
          id="standard-multiline-static"
          label="Tłumaczenie"
          multiline
          rows={1}
          defaultValue=""
          variant="standard"
          sx={{ mt: 2 }}
          onChange={(e) => handleTranslation(e.target.value)}
        />
      </div>
          </Typography>
          <Button id="add-btn" variant="outlined" onClick={() => {sumbitWord()}}>Dodaj</Button>
        </Box>
      </Modal>
    </div>
  );
}
