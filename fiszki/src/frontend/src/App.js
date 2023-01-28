import {useState} from 'react';
import Navbar from './components/Navbar';
import FlashCards from './components/FlashCards';
import LeftDrawer from './components/LeftDrawer';
import AddFlashCardModal from './components/AddFlashCardModal';
import FlashCardsList from './components/FlashCardsList';
import "./style/main.css";
function App() {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [displayModal, setDisplayModal] = useState(false);
  const [mode, setMode] = useState("review");
  return (
    <div className="App">
      <Navbar setDrawerOpen={setDrawerOpen}/>
      <LeftDrawer drawerOpen={drawerOpen} setDrawerOpen={setDrawerOpen} setDisplayModal={setDisplayModal} setMode={setMode}/>
      {mode !== "list" ? 
      <FlashCards mode={mode}/>
       : <FlashCardsList />}
      <AddFlashCardModal open={displayModal} setOpen={setDisplayModal}/>
    </div>
  );
}

export default App;
