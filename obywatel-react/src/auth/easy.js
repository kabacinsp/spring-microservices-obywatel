import React, { Component } from 'react';
import logo from './../logo.svg';
import styles from './easy.module.scss'

class EasyForm extends Component {

  state = {};

   componentDidMount() {
       this.easy();
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

export default EasyForm
