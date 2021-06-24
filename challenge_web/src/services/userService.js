import { UserManager, WebStorageStateStore } from "oidc-client";
import { storeUser, removeUser } from "../slices/authSlice";
import { setAuthHeader } from "../utils/axiosHeaders";

const isSelenium = process.env.REACT_APP_ENV === "selenium";

const config = {
  authority: isSelenium ? process.env.REACT_APP_IDENTITY_URI_SELENIUM : process.env.REACT_APP_IDENTITY_URI_LOCAL,
  client_id: "challenge_web",
  redirect_uri: isSelenium ? process.env.REACT_APP_REDIRECT_URI_SELENIUM : process.env.REACT_APP_REDIRECT_URI_LOCAL,
  response_type: "id_token token",
  scope: "openid profile challengeapi",
  post_logout_redirect_uri: isSelenium ? process.env.REACT_APP_POST_LOGOUT_REDIRECT_URI_SELENIUM : process.env.REACT_APP_POST_LOGOUT_REDIRECT_URI_LOCAL,
  userStore: new WebStorageStateStore({ store: window.localStorage }),
};

const userManager = new UserManager(config);

export async function loadUserFromStorage(store) {
  try {
    let user = await userManager.getUser();
    if (!user) {
      setAuthHeader(null);
      return store.dispatch(removeUser());
    }

    setAuthHeader(user.access_token);
    store.dispatch(storeUser({ name: user.profile.given_name }));
  } catch (e) {
    setAuthHeader(null);
    store.dispatch(removeUser());
  }
}

export function signinRedirect() {
  return userManager.signinRedirect();
}

export function signinRedirectCallback() {
  return userManager.signinRedirectCallback();
}

export function signoutRedirect() {
  return userManager.signoutRedirect();
}

export function signoutRedirectCallback() {
  return userManager.signoutRedirectCallback();
}

export default userManager;
