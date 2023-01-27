import {useState} from 'react';
import Navbar from './components/Navbar';
import FlashCards from './components/FlashCards';
import LeftDrawer from './components/LeftDrawer';
import AddFlashCardModal from './components/AddFlashCardModal';
import "./style/main.css";
function App() {
  const [drawerOpen, setDrawerOpen] = useState(false);
  const [displayModal, setDisplayModal] = useState(false);
  return (
    <div className="App">
      <Navbar setDrawerOpen={setDrawerOpen}/>
      <LeftDrawer drawerOpen={drawerOpen} setDrawerOpen={setDrawerOpen} setDisplayModal={setDisplayModal}/>
      <FlashCards />
      <AddFlashCardModal open={displayModal} setOpen={setDisplayModal}/>
    </div>
  );
}

export default App;
