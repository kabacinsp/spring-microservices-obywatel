import { React, Component } from 'react';
import { Route, Switch, Redirect } from 'react-router-dom';
import Login from './auth/login/Login'
import Easy from './auth/easy';
import SignupForm from './auth/signup/Signup'
import './App.css';
import { getCurrentUser } from './components/utils/APIUtils';
import PrivateRoute from './components/utils/PrivateRoute';

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
    getCurrentUser()
    .then(response => {
      this.setState({
        currentUser: response,
        authenticated: true,
        loading: false
      });
    }).catch(error => {
      this.setState({
        loading: false
      });
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
      <div>
          <Route exact path="/" >
            <Redirect to="/home" />
          </Route>
          <Switch>
            <PrivateRoute path="/home" authenticated={this.state.authenticated} currentUser={this.state.currentUser}
              handleLogout={this.handleLogout} component={Easy}></PrivateRoute>
            <Route path="/login"
              render={(props) => <Login authenticated={this.state.authenticated} {...props} />}></Route>
            <Route path="/signup"
              render={(props) => <SignupForm authenticated={this.state.authenticated} {...props} />}></Route>
          </Switch>
        </div>
    );
  }
}

export default App;
