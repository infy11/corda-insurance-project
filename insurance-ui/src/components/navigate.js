import React, { Component } from 'react'
import { BrowserRouter as Router, Route } from "react-router-dom";
import HDFC from './hdfc'
import Bajaj from './bajaj'
import IRDA from './irda'


export default class Navigate extends Component {
  render() {
    return (
      <div>
      <Router>
    <div>
      

      <Route exact path="/hdfc" component={HDFC} />
      <Route path="/bajaj" component={Bajaj} />
      <Route path="/irda" component={IRDA} />
    </div>
  </Router>
        
 
      </div>
    )
  }
}
