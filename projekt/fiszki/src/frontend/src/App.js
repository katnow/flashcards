import {useState} from 'react';
import Navbar from './components/Navbar';
import FlashCards from './components/FlashCards';
import LeftDrawer from './components/LeftDrawer';
import "./style/main.css";
function App() {
  const [drawerOpen, setDrawerOpen] = useState(false);
  return (
    <div className="App">
      <Navbar setDrawerOpen={setDrawerOpen}/>
      <LeftDrawer drawerOpen={drawerOpen} setDrawerOpen={setDrawerOpen}/>
      <FlashCards />
    </div>
  );
}

export default App;
