import React, { Component } from 'react'
import NewContract from './new-contract'
import Header from './header'
import Details from './details'




export default class HDFC extends Component {
  render() {
    return (
     <div>
         
       <Header img="hdfc-logo.png"/>
   
  
       <NewContract port="10013" party="Bajaj"/>
       <br/>
       <h2> view transactions  </h2>
       <hr/>
       <br/>
       
       <Details port="10013"/>
       <br/><br/>
       
      </div>
    )
  }
}
