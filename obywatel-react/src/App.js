import 'bootstrap/dist/css/bootstrap.min.css';
import logo from './logo.svg';
import './App.css';
import { Navbar, Form, Button } from 'react-bootstrap';


function App() {
  return (
    <Navbar bg="dark">
      <Navbar.Brand href="#home">
        Home
      </Navbar.Brand>
    </Navbar>
  );
}

export default App;
