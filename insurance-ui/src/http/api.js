
export const contract_builder=(chasis,party_name,cust_name,year,idv_value,port,party)=>
{
   let url=`http://localhost:${port}/api/template/create-iou?chasis=${chasis}&partyName=O=${party},L=Mum,C=IN&cust_name=${cust_name}&year=${year}&idv_value=${idv_value}&irda=O=IRDA,L=Mum,C=IN`
    fetch(url,{method:"POST"}).then(response=>{
        console.log(response);
      
    }
    ).catch(err=>{
        console.log(err);
    })

}


//working sample url 
//http://localhost:10013/api/template/create-iou?chasis=1&partyName=O=Bajaj,L=Mum,C=IN&cust_name=varnit&year=2002&idv_value=34&irda=O=IRDA,L=Mum,C=IN
