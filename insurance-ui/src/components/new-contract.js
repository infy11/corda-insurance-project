import React, { Component } from 'react'
import '../../node_modules/bootstrap/dist/css/bootstrap.min.css'
import {contract_builder} from '../http/api'


export default class NewContract extends Component {
  constructor(props){
    super(props);
    

    this.handleSubmit=this.handleSubmit.bind(this);


  }

  handleSubmit(event)
  {
    event.preventDefault();
    
    contract_builder(event.target.chasis.value,event.target.party.value,event.target.customer.value,event.target.year.value,event.target.idv_value.value,this.props.port,this.props.party)

  }
  render() {
    return (
      <div>
        <div className="card shadow cardMargin">
        <div className="card-body">
        <h3 class="card-title">Create Contract</h3>
        <form onSubmit={this.handleSubmit}>
          <div className="form-group" >

          <label htmlFor="chasis">Chasis Number</label>
          <input type="text" className="form-control" id="chasis" name="chasis" placeholder="Enter chasis"/>
         </div>
         <div className="form-group">
          <label htmlFor="party">Party Name</label>
          <input type="text" className="form-control" id="party" name="party" placeholder="Enter party name"/>
        </div>
         <div className="form-group">
          <label htmlFor="chasis">Customer Name</label>
          <input type="text" className="form-control" id="customer" name="customer" placeholder="Enter customer name"/>
        </div>
        <div className="form-group">
          <label htmlFor="year">year</label>
          <input type="text" className="form-control" id="year" name="year" placeholder="Enter year"/>
        </div>
        <div className="form-group">
          <label htmlFor="idv value">IDV value</label>
          <input type="text" className="form-control" id="idv value" name="idv_value" placeholder="Enter idv value"/>
        </div>

        <button type="submit" class="btn btn-primary" >Submit</button>


          
          
          </form>
          </div>

          </div>
        


      </div>
    )
  }
}
