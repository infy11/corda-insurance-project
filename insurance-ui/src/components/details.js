import React, { Component } from 'react'
import { AgGridReact } from 'ag-grid-react';
import 'ag-grid/dist/styles/ag-grid.css';
import 'ag-grid/dist/styles/ag-theme-material.css';

export default class Details extends Component {
  constructor(props){
    super(props);

    this.state = {
      
      columnDefs: [
          {headerName: "car chasis", field: "state.data.car_chasis"
        },
          {headerName: "customer name", field: "state.data.cust_name"},
          {headerName: "year", field: "state.data.year"},
          {headerName: "idv_value", field: "state.data.idv_value"},
          {headerName: "participants", field: "state.data.participants"}

      ],
      rowData: [
        
      ]
  }
  }
  componentDidMount() {
          let urlbuilder="http://localhost:"+this.props.port+"/api/template/ious";
          fetch(urlbuilder,{method:"GET"})
                .then(result => result.json())
                .then(rowData => {this.setState({rowData}) 
             })
        }
  render() {
    return (
      <div className="card shadow">

          <div 
                  
                  className="ag-theme-material table table-striped card-body"
                  style={{ 
	               
	                width: '100%' }} 
		            >
                    <AgGridReact
                        enableSorting={true}
                        enableFilter={true}
                        enableColResize={true}
                        gridAutoHeight={true}
                        columnDefs={this.state.columnDefs}
                        rowData={this.state.rowData}>
                    </AgGridReact>
                    
                </div>

            
      
      </div>

    )
  }
}
