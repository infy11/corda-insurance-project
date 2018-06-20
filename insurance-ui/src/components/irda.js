import React, { Component } from 'react'
import Header from './header'
import Details from './details'

export default class IRDA extends Component {
  render() {
    return (
      <div>
        <div>
          <div>
         
         <Header img="irda-logo.jpg"/>
    
         <h2> view transactions  </h2>
         <hr/>
         <br/>
         
         <Details port="10010"/>
         <br/><br/>
        </div>
      </div>
      </div>
    )
  }
}
