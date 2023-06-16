import {
    Outlet,
    NavLink,
    useLoaderData,
    useNavigation,
  } from "react-router-dom";

  import { getContacts } from "../contacts";
    
export async function loader() {
  const contacts = await getContacts();
  return { contacts };
}

export default function Root() {
     const { contacts } = useLoaderData();
     const navigation = useNavigation();
    return (
      <>
        <div id="sidebar">
          <nav>
          {contacts.length ? (
            <ul>
              {contacts.map((contact) => (
                <li key={contact.id}>
                  <NavLink
                    to={`contacts/${contact.id}`}
                    className={({ isActive, isPending }) =>
                      isActive
                        ? "active"
                        : isPending
                        ? "pending"
                        : ""
                    }
                  >
                    {contact.firstName || contact.lastName ? (
                      <>
                        {contact.firstName} {contact.lastName}
                      </>
                    ) : (
                      <i>No Name</i>
                    )}{" "}
                    {contact.favorite && <span>★</span>}
                    </NavLink>
                </li>
              ))}
            </ul>
          ) : (
            <p>
              <i>No contacts</i>
            </p>
          )}
          <li>
          <NavLink   to={`movements/`}>
          <i>All Movements</i>
          </NavLink>
          
          </li>
        </nav>
        </div>
        <div
        id="detail"
        className={
          navigation.state === "loading" ? "loading" : ""
        }
      >
        <Outlet />
        </div>
      </>
    );
  }