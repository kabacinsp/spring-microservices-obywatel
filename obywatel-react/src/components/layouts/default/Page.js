import { React, Component } from 'react';
import NavBar from './../../layouts/navbar/navbar';
import styles from './Page.module.scss';


const Page = ({ children }) => (
  <div>
      <NavBar />
      <div className={styles.container}>
        {children}
      </div>
  </div>
);

export default Page;
