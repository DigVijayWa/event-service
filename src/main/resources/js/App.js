import React, { Component, useState, useEffect } from "react";
import { Router, Route, Link, IndexRoute } from "react-router";
import ReactDOM from "react-dom";
import { BrowserRouter } from "react-router-dom";
import "./css/App.css";

function App() {
 
  return (
    <div className="app">
        <p>This tag reminds me of the lion. </p>
    </div>
  );
}
export default App;

ReactDOM.render(<App />, document.getElementById("react"));
