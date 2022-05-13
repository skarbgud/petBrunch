import { BrowserRouter, Route, Routes } from "react-router-dom";
import logo from './logo.svg';
import './App.css';
import Home from "./pages/Home";
import Community from './pages/Community'
import Header from './components/Header'

function App() {
  return (
      <BrowserRouter>
        <div className="App">
          <Header></Header>
          <Routes>
            <Route exact path="/" element={<Home />}></Route>
            <Route exact path="/community" element={<Community />}></Route>
          </Routes>
        </div>
      </BrowserRouter>
  );
}

export default App;
