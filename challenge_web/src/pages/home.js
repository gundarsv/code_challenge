import React, { useEffect, useState } from "react";
import { signoutRedirect } from "../services/userService";
import { useSelector } from "react-redux";
import * as apiService from "../services/apiService";
import { Container } from "@material-ui/core";
import Table from "../components/table";

function Home() {
  const user = useSelector((state) => state.auth.user);
  const [accounts, setAccounts] = useState(null);
  const data = React.useMemo(() => (accounts ? accounts : []), [accounts]);

  const columns = React.useMemo(
    () => [
      {
        Header: () => null,
        id: "delete",
        Cell: ({ row }) => (
          <button onClick={() => deleteAccount(row.original.id)}>Delete</button>
        ),
      },
      {
        Header: "Id",
        accessor: "id",
      },
      {
        Header: "Name",
        accessor: "name",
      },
      {
        Header: "Phone",
        accessor: "phone",
      },
      {
        Header: "Email",
        accessor: "email",
      },
      {
        Header: "Address",
        accessor: "address",
      },
      {
        Header: "Country",
        accessor: "country",
      },
      {
        Header: "Department",
        accessor: "department",
      },
    ],
    []
  );

  function signOut() {
    signoutRedirect();
  }

  async function deleteAccount(id) {
    await apiService.deleteAccount(id).then((response) => {
      if (response.status === 200) {
        getAccounts();
      }
    });
  }

  async function getAccounts() {
    const accountsFromApi = await apiService.getAccounts();
    setAccounts(accountsFromApi);
  }

  useEffect(() => {
    getAccounts();
  }, []);

  return (
    <div>
      <div style={{ textAlign: "center" }}>
        <h1>Home</h1>
        <p>Hello, {user.name}.</p>
        <button className="button button-clear" onClick={() => signOut()}>
          Sign Out
        </button>
      </div>

      <Container>
        <Table columns={columns} data={data} />
        <label htmlFor="name-input">Name</label>
        <input id="name-input"></input>
      </Container>
    </div>
  );
}

export default Home;
