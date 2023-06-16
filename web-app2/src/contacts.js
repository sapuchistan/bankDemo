import axios from "axios";


const client1 = axios.create({
    baseURL: "http://localhost:8080/v1/bank" 
  });

const client2 = axios.create({
    baseURL: "http://localhost:8080/v1/bank/customers" 
  });

const client3 = axios.create({
    baseURL: "http://localhost:8080/v1/bank/transactional/deposit" 
  });

const client4 = axios.create({
    baseURL: "http://localhost:8080/v1/bank/transactional/withdraw" 
  });

const client5 = axios.create({
    baseURL: "http://localhost:8080/v1/bank/transactional/transfer" 
  });

const client6 = axios.create({
    baseURL: "http://localhost:8080/v1/bank/movements" 
  });

  export async function getCustomers() {
    const response = await client1.get("");
    return response.data;
  }

  export async function getCustomer(data) {
    const response = await client2.get("/"+data);
    return response.data;
  }

  export async function atmDeposit(data, params) {
   
    const response = await client3.put("",{"idCustomer":data,
    credit:params.Credit});
    return response.data;
  }

  export async function atmWithdraw(data, params) {
   
    const response = await client4.put("",{"idCustomer":data,
    debit:params.Debit});
    return response.data;
  }

  export async function atmTransfer(data, params) {
   
    const response = await client5.put("",{idCustomer:data,
    debit:params.Debit, accountNumber: params.Account});
    return response.data;
  }

  export async function movements(data) { 
    const response = await client6.get("", 
    {params:{
      page: data
    }
});
    return response.data;
  }

  export async function movementsPerCustomer(customer,data) { 
    const response = await client6.get("/"+customer, 
    {params:{
      page: data
    }
});
    return response.data;
  }



export async function getContacts() {
    let contacts = await getCustomers();
    return contacts.content;
}

export async function getContact(id) {   
    let contacts = await getCustomer(id);
    return contacts;
  }

  export async function DepositEvent(data, params) {   
    let contacts = await atmDeposit(data, params);
    return contacts;
  }

  export async function WithdrawEvent(data, params) {   
    let contacts = await atmWithdraw(data, params);
    return contacts;
  }

  export async function TransferEvent(data, params) {   
    let contacts = await atmTransfer(data, params);
    return contacts;
  }

  export async function getMovements(page) {   
    if(!page){
        page=0;
    }
    let contacts = await movements(page);
    return contacts;
  }

  export async function getMovementsPerCustomer(customer,page) {   
    if(!page){
        page=0;
    }
    let contacts = await movementsPerCustomer(customer, page);
    return contacts;
  }












