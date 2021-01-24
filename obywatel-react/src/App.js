import React, {Component} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import logo from './logo.svg';
import './App.css';
import { Navbar, Form, Button } from 'react-bootstrap';
import { getAllUsers } from './components/utils/APIUtils'


class App extends Component {

    state = {};

    componentDidMount() {
        setInterval(this.easy, 250);
    }

    easy = () => {
        fetch('/auth/easy')
            .then(response => response.text())
            .then(message => {
                this.setState({message: message});
            });
    };

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>
                    <h1 className="App-title">{this.state.message}</h1>
                </header>
                <p className="App-intro">
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>
            </div>
        );
    }
}

export default App;
