import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import styles from './easy.module.scss'
import { easy } from '../components/utils/APIUtils.js';

class EasyForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            how: ''
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
    alert('A name was submitted: ' + this.state.how);

    const signupRequest = Object.assign({}, this.state);

    easy()
    .then(response => {
      this.setState({
        how: response
      });
    })
  }

    render() {
        return (
            <div className={styles.login_container}>
              <Form onSubmit={this.handleSubmit}>
                <Form.Group controlId="formBasicEmail">
                  <Form.Label>{this.state.how}</Form.Label>
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

  export default EasyForm
