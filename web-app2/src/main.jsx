import * as React from "react";
import * as ReactDOM from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router-dom";
import "./index.css";

/* existing imports */

import ErrorPage from "./error-page";

import Index from "./routes/index";

import Contact, {
  loader as contactLoader,
} from "./routes/contact";

import Root, {
  loader as rootLoader,
} from "./routes/root";

import Deposit, {
  action as depositAction,
} from "./routes/deposit";

import Withdraw, {
  action as withdrawAction,
} from "./routes/withdraw";

import Transfer, {
  action as transferAction,
} from "./routes/transfer";

import Movement, {
  loader as movementLoader
}  from "./routes/movements";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    errorElement: <ErrorPage />,
    loader: rootLoader,
    children: [
      { index: true, element: <Index /> },
      {
        path: "contacts/:contactId",
        element: <Contact />,
        loader: contactLoader,
      },
      {
        path: "contacts/:contactId/deposit",
        element: <Deposit />,
        loader: contactLoader,
        action: depositAction,
      },
      {
        path: "contacts/:contactId/withdraw",
        element: <Withdraw />,
        loader: contactLoader,
        action: withdrawAction,
      },
      {
        path: "contacts/:contactId/transfer",
        element: <Transfer />,
        loader: contactLoader,
        action: transferAction,
      },
      {
        path: "movements/",
        element: <Movement />,
        loader: movementLoader,
      },   
    ],
  },
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);