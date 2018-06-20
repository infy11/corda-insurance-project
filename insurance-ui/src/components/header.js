import React, { Component } from 'react'

export default class Header extends Component {
  constructor(props)
  {
    super(props);
    this.state={

    }

  }
  render() {
    return (
      <div className="page-header header-margin" >
      <img  src={"images/" + this.props.img} width="300px" height="120px" alt={this.props.img}/>
      <hr/>

          
        
      </div>
    )
  }
}
