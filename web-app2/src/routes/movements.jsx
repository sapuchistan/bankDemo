import {
    Form,
  } from "react-router-dom";  

  import { useState, useEffect } from "react";

  import { getMovements } from "../contacts";

  export async function loader( {param }) {
    console.log(param);
    const contact = await getMovements(param);
    return { contact };
  }
  

  export default function Movement() {
    const [contact, setContact] = useState("");
    const [name, setName] = useState("");
    
    useEffect(() => {
   
        setName(0);
    
      }, []);
   
    useEffect(() => {
        const contactReturned =  getMovements(name);

        contactReturned.then((value) => {           
            setContact(value);
          });

      }, [name]);
  
  return (
  
    <>
   <Form method="post" id="contact-form"> 
   <select  onChange={(e) => setName(e.target.value)}>
   { Array.apply(0, Array(contact.totalPages)).map((key, index) =>
  <option key={index} value={index}> 
   {index}
   </option>
   
  

)}
 </select>
 <table >
<thead>
  <tr>
    <td >Date </td>
    <td >Debit </td>
    <td >Credit </td>
    <td >Balance </td>
    <td >AccountNumber </td>
    <td >Movement type </td>
  </tr>
</thead>
<tbody>
    {contact.content?.map((value,key)=>
    <tr key={key}>
    <td >{value.timeStamp} </td>
    <td >{value.debit} </td>
    <td >{value.credit} </td>
    <td >{value.balance} </td>
    <td >{value.accountNumber} </td>
    <td >{value.movementType} </td>
    </tr>
    )}

</tbody>
</table>
   </Form>
 
</>
);
}