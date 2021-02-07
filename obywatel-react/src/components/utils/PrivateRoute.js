import React from 'react';
import { Route, Redirect } from "react-router-dom";
import NavBar from '../layouts/navbar/navbar';

const PrivateRoute = ({ component: Component, authenticated, handleLogout, ...rest }) => (
    <Route
      {...rest}
      render={props =>
        authenticated ? (
          <div>
            <NavBar authenticated={authenticated} onLogout={handleLogout} />
            <Component {...rest} {...props} />
          </div>
        ) : (
          <Redirect
            to={{
              pathname: '/login',
              state: { from: props.location }
            }}
          />
        )
      }
    />
);

export default PrivateRoute
