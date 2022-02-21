import axios from "axios";

export const sendGetRequest = (url, setResponse) => {
  axios
    .get(url)
    .then((res) => setResponse(JSON.stringify(res.data)))
    .catch((err) => console.log(err));
};
