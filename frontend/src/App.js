import React, { useEffect, useState } from "react";
import Navbar from "./components/Navbar";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import { Jokes, Signup, Login, Home, Admin } from "./components";
import { getUserByJwt, setToken } from "./utils/token";
import { loginMethod, logoutMethode } from './utils/loginUtils'
import {addCourseMethod, addClasseMethod} from './utils/courseUtils'

function App() {
  const [error, setError] = useState("");
  const init = { username: "", roles: [] };
  const [user, setUser] = useState({ ...init });
  const login = (user, pass) => loginMethod(user, pass, setUser);
  const logout = () => logoutMethode(setUser, init);



  useEffect(() => {
    if (getUserByJwt()) {
      setUser(getUserByJwt())
    }
  }, []);

  return (
    <>
      <Router>
        <Navbar user={user} logout={logout} />
        <Switch>
          <Container fluid>
            <Route path="/" exact>
              <Home />
            </Route>
            <Route path="/jokes">
              <Jokes />
            </Route>

            <Route path="/admin">
              <Admin addCourse={addCourseMethod} addClass={addClasseMethod}/>
            </Route>

            <Route path="/products" />


            <Route path="/signin">
              <Login login={login} user={user} logout={logout} />
            </Route>
            <Route path="/signup">
              <Signup />
            </Route>
          </Container>
        </Switch>
      </Router>
    </>
  );
}

export default App;
