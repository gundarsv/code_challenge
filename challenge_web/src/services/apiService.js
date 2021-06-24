import axios from "axios";

const isSelenium = process.env.REACT_APP_ENV === "selenium";

async function getAccounts() {
  const response = await axios.get(
    `${isSelenium ? process.env.REACT_APP_API_URI_SELENIUM : process.env.REACT_APP_API_URI_LOCAL}/api/v1/account`
  );
  return response.data;
}

async function deleteAccount(id) {
  return await axios.delete(
    `${isSelenium ? process.env.REACT_APP_API_URI_SELENIUM : process.env.REACT_APP_API_URI_LOCAL}/api/v1/account/${id}`
  );
}

export { getAccounts, deleteAccount };
