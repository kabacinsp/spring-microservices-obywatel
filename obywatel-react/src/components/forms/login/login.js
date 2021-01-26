import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import styles from './login.module.scss'
import { login } from '../../utils/APIUtils.js';

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
         alert("You're successfully logged!");
         this.props.history.push("/");});
    }

    render() {
        return (
            <div className={styles.login_container}>
              <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                  <Form.Label>Email address</Form.Label>
                  <Form.Control type="email" placeholder="Enter email" name="email"
                                onChange={this.handleInputChange}
                                value={this.state.email} required/>
                </Form.Group>

                <Form.Group controlId="formBasicPassword">
                  <Form.Label>Password</Form.Label>
                  <Form.Control type="password" placeholder="Password" name="password"
                                onChange={this.handleInputChange}
                                value={this.state.password} required/>
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
