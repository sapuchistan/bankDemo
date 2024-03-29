import {
    Form,
    redirect,
    useNavigate,
  } from "react-router-dom";  

  import { DepositEvent } from "../contacts";
  
  export async function action({ request, params }) {
    const formData = await request.formData();
    const updates = Object.fromEntries(formData);
    await DepositEvent(params.contactId, updates);
    return redirect(`/contacts/${params.contactId}`);
  }


  export default function Deposit() {
    const navigate = useNavigate();
  
  return (

   
    <Form method="post" id="contact-form">
    
        <h5>Deposit Amount:</h5>
        <div> 
        <input
          placeholder="$ 0.00"
          aria-label="amount"
          type="numeric"
          name="Credit"
          defaultValue={"0.00"}
        />
      
      </div>
      
      
       
      <div> 
        <button type="submit">Save</button>
        <button
          type="button"
          onClick={() => {
            navigate(-1);
          }}
        >
          Cancel
        </button>
       </div>
    </Form>
     
  );
}