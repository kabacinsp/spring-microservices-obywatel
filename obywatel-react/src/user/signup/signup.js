import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import styles from './signup.module.scss'
import { signup } from '../../components/utils/APIUtils.js';

class SignupForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            name: '',
            surname: '',
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
      alert('A name was submitted: ' + this.state.name);

      const signupRequest = Object.assign({}, this.state);

      signup(signupRequest)
       .then(response => {
         this.props.history.push("/");});
    }

    render() {
        return (
            <div className={styles.login_container}>
              <Form onSubmit={this.handleSubmit}>
              <Form.Group controlId="formBasicName">
                <Form.Label>Name</Form.Label>
                <Form.Control placeholder="Enter your name" name="name"
                              onChange={this.handleInputChange}
                              value={this.state.name} required/>
              </Form.Group>
              <Form.Group controlId="formBasicSurname">
                <Form.Label>Surname</Form.Label>
                <Form.Control placeholder="Enter your surname" name="surname"
                              onChange={this.handleInputChange}
                              value={this.state.surname} required/>
              </Form.Group>
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

  export default SignupForm
