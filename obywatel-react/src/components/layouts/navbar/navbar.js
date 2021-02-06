import { React, Component } from 'react'
import { Navbar, Nav, NavDropdown } from 'react-bootstrap'
import { Link } from 'react-router-dom';

class AppHeader extends Component {
  render() {
      return (
        <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark">
        <Navbar.Brand href="#home">Obywatel</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="mr-auto">
            <Nav.Link as={Link} to="/newlogin">Features</Nav.Link>
            <Nav.Link as={Link} to="basicauth">Pricing</Nav.Link>
            <NavDropdown title="Dropdown" id="collasible-nav-dropdown">
              <NavDropdown.Item href="#action/3.1">Action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
              <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
              <NavDropdown.Divider />
              <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>
            </NavDropdown>
          </Nav>
          <Nav>
            { this.props.authenticated ? (
              <NavDropdown title="Profile" id="profile-drop">
                <NavDropdown.Divider />
                <NavDropdown.Item onClick={this.props.onLogout}>Logout</NavDropdown.Item>
              </NavDropdown>
            ): (
              <Nav className="mr-auto">
                <Nav.Link as={Link} to="/login">Login</Nav.Link>
                <Nav.Link as={Link} to="/signup">Signup</Nav.Link>
              </Nav>
            )}
          </Nav>
        </Navbar.Collapse>
      </Navbar>
    )
  }
}

export default AppHeader;
