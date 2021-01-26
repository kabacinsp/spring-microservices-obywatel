import React from 'react';
import ReactDOM from 'react-dom';
import Layout from './components/layouts/mainLayout/layout';
import NavBar from './components/layouts/navbar/navbar';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter as Router } from 'react-router-dom';

ReactDOM.render(
  <React.StrictMode>
  <Router>
    <Layout>
      <NavBar />
      <App />
    </Layout>
    </Router>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
