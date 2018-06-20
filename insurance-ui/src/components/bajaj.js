import React, { Component } from 'react'
import NewContract from './new-contract'
import Header from './header'
import Details from './details'
export default class Bajaj extends Component {
  render() {
    return (
      <div>
          <div>
         
         <Header img="bajaj-logo.jpg"/>
     
    
         <NewContract port="10007" party="Hdfc"/>
         <br/>
         <h2> view transactions  </h2>
         <hr/>
         <br/>
         
         <Details port="10007"/>
         <br/><br/>
        </div>
      </div>
    )
  }
}
