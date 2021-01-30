import React, { Component } from 'react';
import { Form, Button } from 'react-bootstrap';
import logo from './../logo.svg';
import styles from './easy.module.scss'
import { easy } from '../components/utils/APIUtils.js';
import Page from '../components/layouts/default/Page'

class EasyForm extends Component {

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
         <Page>
           <div className="App">
               <header className="App-header">
                   <img src={logo} className="App-logo" alt="logo"/>
                   <h1 className="App-title">{this.state.message}</h1>
               </header>
               <p className="App-intro">
                   To get started, edit <code>src/App.js</code> and save to reload.
               </p>
           </div>
          </Page>
       );
   }
  }

  export default EasyForm
