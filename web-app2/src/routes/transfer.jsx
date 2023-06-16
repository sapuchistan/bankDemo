import {
    Form,
    redirect,
    useNavigate,
  } from "react-router-dom";
  

  import { TransferEvent } from "../contacts";
  
  export async function action({ request, params }) {
    const formData = await request.formData();
    const updates = Object.fromEntries(formData);
    await TransferEvent(params.contactId, updates);
    return redirect(`/contacts/${params.contactId}`);
  }


  export default function Transfer() {
    const navigate = useNavigate();
  
  return (
    <Form method="post" id="contact-form">
<div>
        <h3>Transfer Amount </h3>
        <input
          placeholder="$ 0.00"
          aria-label="amount"
          type="numeric"
          name="Debit"
          defaultValue={"0.00"}
        />
        </div>
        <div>

         <h3>Recipient account Number 57 1 101 </h3> 
        <input
          placeholder="0"
          aria-label="amount"
          type="numeric"
          name="Account"
          defaultValue={"0"}
        />
        
        </div>
      <p>
        <button type="submit">Save</button>
        <button
          type="button"
          onClick={() => {
            navigate(-1);
          }}
        >
          Cancel
        </button>
      </p>
    </Form>
  );
}