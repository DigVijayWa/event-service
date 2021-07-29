import React, { Component, useState, useEffect } from "react";
import { Router, Route, Link, IndexRoute } from "react-router";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import "./css/App.css";
import { postRequest } from "./utility/PostRequest";
import { RequestType } from "./utility/RequestType";
import FingerprintJS from "@fingerprintjs/fingerprintjs";
import SearchAppBar from "./components/SearchAppBar";
import { makeStyles } from "@material-ui/core/styles";
import LandingCard from "./components/LandingCard";
import Grid from '@material-ui/core/Grid';


const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
}));

function App() {
  const classes = useStyles();

  const [events, setEvents] = useState(() => []);

  const [userId, setUserId] = useState(() => null);

  const [accessToken, setAccessToken] = useState(() => null);

  useEffect(() => {
    (async () => {
      // We recommend to call `load` at application startup.
      const fp = await FingerprintJS.load();

      // The FingerprintJS agent is ready.
      // Get a visitor identifier when you'd like to.
      const result = await fp.get();

      // This is the visitor identifier:

      setUserId(result.visitorId);
    })();
  }, []);

  useEffect(() => {
    if (accessToken !== null) fetchAllEvents();
  }, [accessToken]);

  useEffect(() => {
    (async () => {
      if (userId !== null) {
        const res = await postRequest(RequestType.UserSignIn, {
          username: `${userId}`,
          password: "pleasework",
        });
        setAccessToken(res.data);
      }
    })();
  }, [userId]);

  function fetchAllEvents() {
    (async () => {
      if (accessToken !== null) {
        let response = await postRequest(RequestType.GetAllEvents, {
          accessToken,
        });
        console.log(response);
      }
    })();
  }

  return (
    <div className="app">
      <div className={classes.root}>
        <SearchAppBar />
        <Grid
          container
          spacing={0}
          direction="column"
          alignItems="center"
          justify="center"
          style={{ minHeight: "100vh" }}
        >
          <Grid item xs={3}>
            <div
              style={{
                display: "flex",
                justifyContent: "center",
              }}
            >
              <LandingCard />
            </div>
          </Grid>
        </Grid>
      </div>
    </div>
  );
}
export default App;

ReactDOM.render(<App />, document.getElementById("react"));
