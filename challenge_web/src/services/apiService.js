import axios from "axios";

async function getAccounts() {
  const response = await axios.get(
    `${process.env.REACT_APP_API_URI}:${process.env.REACT_APP_API_PORT}/api/v1/account`
  );
  return response.data;
}

async function deleteAccount(id) {
  return await axios.delete(
    `${process.env.REACT_APP_API_URI}:${process.env.REACT_APP_API_PORT}/api/v1/account/${id}`
  );
}

export { getAccounts, deleteAccount };
