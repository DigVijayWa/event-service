import { RequestType } from "./RequestType";
import axios from "axios";

export const postRequest = async function (type, data) {
  const config = ((data) => {
    return data.accessToken !== undefined
      ? {
          headers: { Authorization: `Bearer ${data.accessToken}` },
        }
      : undefined;
  })(data);
  try {
    switch (type) {
      case RequestType.UserSignIn:
        return await axios.post(type.url, data, config);
      case RequestType.CreateEvent:
        return await axios.post(type.url, data, config);
      case RequestType.DeleteEvent:
        return await axios.delete(type.url.replace("{id}", data.id), config);
      case RequestType.GetAllEvents:
        return await axios.get(type.url, config);
      case RequestType.GetSpecificEvent:
        return await axios.get(type.url.replace("{id}", data.id), config);
      case RequestType.ReplaceEvent:
        return await axios.put(type.url.replace("{id}", data.id), config);
      case RequestType.DeleteUser: 
        return await axios.delete(type.url, data, config);
    }
  } catch (err) {
    console.error("error while requesting data ", type, err);
    return {};
  }
};
