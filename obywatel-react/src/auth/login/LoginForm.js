import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import styles from './Login.module.scss'
import { login } from '../../components/utils/APIUtils.js';
import { ACCESS_TOKEN } from '../../components/common/constants';

class LoginForm extends Component {

constructor(props) {
       super(props);
       this.state = {
           email: '',
           password: ''
       };
       this.handleInputChange = this.handleInputChange.bind(this);
       this.handleSubmit = this.handleSubmit.bind(this);
   }

   handleInputChange(event) {
       const target = event.target;
       const inputName = target.name;
       const inputValue = target.value;

       this.setState({
           [inputName] : inputValue
       });
   }

   handleSubmit(event) {
       event.preventDefault();

       const loginRequest = Object.assign({}, this.state);

       login(loginRequest)
       .then(response => {
         localStorage.setItem(ACCESS_TOKEN, response.accessToken);
         alert("You're successfully logged in!");
         this.props.history.push("/");
         window.location.href = "/";
       }).catch(error => {
         alert((error && error.message) || 'Oops! Something went wrong. Please try again!');
       });
     }

    render() {
        return (
            <div className={styles.login_container}>
              <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control type="email" placeholder="Enter email" name="email"
                                value={this.state.email}
                                onChange={this.handleInputChange} required/>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" placeholder="Password" name="password"
                                value={this.state.password} onChange={this.handleInputChange} required/>
                </Form.Group>
                <Button variant="primary" type="submit" className="align-center">
                  Submit
                </Button>
              </Form>
            </div>
        );
    }
  }

  export default LoginForm
