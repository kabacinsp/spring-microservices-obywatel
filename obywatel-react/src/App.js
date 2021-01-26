import { React, Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import Login from './components/forms/login/login'
import NavBar from './components/layouts/navbar/navbar';
import './App.css';

class App extends Component {
  render() {
    return (
      <div className="container">
        <div className="app-body">
          <Switch>
            <Route path="/login"
              render={(props) => <Login />}></Route>
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
