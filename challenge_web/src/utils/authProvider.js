import React, { useEffect, useRef } from "react";
import { useDispatch } from "react-redux";
import { setAuthHeader } from "./axiosHeaders";

import { storeUser, removeUser } from "../slices/authSlice";

export default function AuthProvider({
  userManager: manager,
  store,
  children,
}) {
  let userManager = useRef();

  const dispatch = useDispatch();

  useEffect(() => {
    userManager.current = manager;

    const onUserLoaded = (user) => {
      dispatch(storeUser({ name: user.profile.given_name }));
      setAuthHeader(user.access_token);
    };

    const onUserUnloaded = () => {
      setAuthHeader(null);
    };

    const onAccessTokenExpiring = () => {
      console.log(`user token expiring`);
    };

    const onAccessTokenExpired = () => {
      dispatch(removeUser(null));
    };

    const onUserSignedOut = () => {
      dispatch(removeUser(null));
    };

    // events for user
    userManager.current.events.addUserLoaded(onUserLoaded);
    userManager.current.events.addUserUnloaded(onUserUnloaded);
    userManager.current.events.addAccessTokenExpiring(onAccessTokenExpiring);
    userManager.current.events.addAccessTokenExpired(onAccessTokenExpired);
    userManager.current.events.addUserSignedOut(onUserSignedOut);

    // Specify how to clean up after this effect:
    return function cleanup() {
      userManager.current.events.removeUserLoaded(onUserLoaded);
      userManager.current.events.removeUserUnloaded(onUserUnloaded);
      userManager.current.events.removeAccessTokenExpiring(
        onAccessTokenExpiring
      );
      userManager.current.events.removeAccessTokenExpired(onAccessTokenExpired);
      userManager.current.events.removeUserSignedOut(onUserSignedOut);
    };
  }, [manager, store, dispatch]);

  return React.Children.only(children);
}
