export const RequestType = Object.freeze({
  UserSignIn: {
    id: 0,
    url: "/users/signin",
    type: "POST",
  },
  GetAllEvents: {
    id: 1,
    url: "/eventObjects",
    type: "GET",
  },
  GetSpecificEvent: {
    id: 2,
    url: "/eventObjects/{id}",
    type: "GET",
  },
  CreateEvent: {
    id: 3,
    url: "/eventObjects/",
    type: "POST",
  },
  DeleteEvent: {
    id: 4,
    url: "/eventObjects/{id}",
    type: "DELETE",
  },
  ReplaceEvent: {
    id: 5,
    url: "/eventObjects/{id}",
    type: "PUT",
  },
  UserSignUp: {
    id: 6,
    url: "/users/signup",
    type: "POST",
  },
  DeleteUser: {
    id: 7,
    url: "/users/delete",
    type: "DELETE",
  },
});
