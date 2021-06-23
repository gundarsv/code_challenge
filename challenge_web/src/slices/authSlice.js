import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  user: undefined,
  isLoadingUser: false,
};

const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    storeUser(state, action) {
      state.isLoadingUser = false;
      state.user = action.payload;
    },
    setLoading(state, action) {
      state.isLoadingUser = true;
    },
    removeUser(state, action) {
      state.user = null;
      state.isLoadingUser = false;
    },
  },
});

export const { storeUser, setLoading, removeUser } = authSlice.actions;

export default authSlice.reducer;
