import { Form, useLoaderData } from "react-router-dom";
import { getContact } from "../contacts";
import { useState, useEffect } from "react";
import { getMovementsPerCustomer } from "../contacts";


export async function loader({ params }) {
    const contact = await getContact(params.contactId);
    return { contact };
  }
  
  
  export default function Contact() {
    const { contact } = useLoaderData();
    const [contactTable, setContactTable] = useState("");
    const [name, setName] = useState("");

    useEffect(() => {
   
        setName(0);
    
      }, [contact.accountNumber]);
   
    useEffect(() => {
        const contactReturned =  getMovementsPerCustomer(contact.accountNumber,name);

        contactReturned.then((value) => {           
            setContactTable(value);
          });

      }, [name,contact]);
  return (
    <>
    <div id="contact" style={{height: '20%'}}>
      <div style={{
          width: '30%',
          height: '100%',
          backgroundColor: 'powderblue',
          paddingLeft: '5%',
          paddingTop: '5%',
        }}>
      <h2>Balance:</h2>
      <h1>
          {contact.balance    ? (
            <>
              {contact.balance}
            </>
          ) : (
            <i>0</i>
          )}{" "}
        </h1>
      </div>

      <div style={{
          width: '70%',
          height: '100%',
          backgroundColor: 'skyblue',
          paddingLeft: '20%',
          paddingTop: '5%',
          
        }}>
      <div>
      <h1>Account number:</h1>
      </div>
        <h1>
          {contact.accountCountryNumber || contact.accountCityNumber ||  contact.accountOfficeNumber || contact.accountNumber   ? (
            <>
              {contact.accountCountryNumber} {contact.accountCityNumber} {contact.accountOfficeNumber} {contact.accountNumber}
            </>
          ) : (
            <i>No Name</i>
          )}{" "}          
        </h1>
        <div >
          <Form action="deposit">
            <button type="submit">deposit</button>
          </Form>
          <Form action="withdraw">
            <button type="submit">withdraw</button>
          </Form>
          <Form action="transfer">
            <button type="submit">transfer</button>
          </Form>
          
        </div>
      </div>
    </div>
    
    <div>
      <h1>Transaction history</h1>
      </div>
     <Form method="post" id="contact-form"> 
     <h5>Select page:</h5>
     <select  onChange={(e) => setName(e.target.value)}>
     { Array.apply(0, Array(contactTable.totalPages)).map((key, index) =>
    <option key={index} value={index}> 
     {index}
     </option>
     
    
  
  )}
   </select>
   <table  style={{         
          backgroundColor: 'skyblue',
        }}>
  <thead style={{        
          backgroundColor: 'black',
          color: 'white'
        }}>
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
      {contactTable.content?.map((value,key)=>
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


