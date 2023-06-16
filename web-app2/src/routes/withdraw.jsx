import {
    Form,
    redirect,
    useNavigate,
  } from "react-router-dom";
  

  import { WithdrawEvent } from "../contacts";
  
  export async function action({ request, params }) {
    const formData = await request.formData();
    const updates = Object.fromEntries(formData);
    await WithdrawEvent(params.contactId, updates);
    return redirect(`/contacts/${params.contactId}`);
  }


  export default function Withdraw() {
    const navigate = useNavigate();
  
  return (
    <Form method="post" id="contact-form">
     
        <span> <h5>Withdraw Amount:</h5></span>
        <div>
        <input
          placeholder="$ 0.00"
          aria-label="amount"
          type="numeric"
          name="Debit"
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