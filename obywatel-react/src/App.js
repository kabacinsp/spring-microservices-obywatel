import { React, Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginForm from './auth/login/Login'
import SignupForm from './auth/signup/Signup'
import NavBar from './components/layouts/navbar/navbar';
import './App.css';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      authenticated: false,
      currentUser: null,
      loading: false
    }

    this.loadCurrentlyLoggedInUser = this.loadCurrentlyLoggedInUser.bind(this);
    this.handleLogout = this.handleLogout.bind(this);
  }

  loadCurrentlyLoggedInUser() {
    this.setState({
      loading: true
    });
  }

  handleLogout() {
    this.setState({
      authenticated: false,
      currentUser: null
    });
    alert("You're safely logged out!");
  }

  componentDidMount() {
    this.loadCurrentlyLoggedInUser();
  }

  render() {
    return (
      <div className="app">
          <NavBar authenticated={this.state.authenticated} onLogout={this.handleLogout} />
        <div className="app-body">
          <Switch>
            <Route path="/login"
              render={(props) => <LoginForm authenticated={this.state.authenticated} {...props} />}></Route>
            <Route path="/signup"
              render={(props) => <SignupForm authenticated={this.state.authenticated} {...props} />}></Route>
          </Switch>
        </div>
      </div>
    );
  }
}

export default App;
