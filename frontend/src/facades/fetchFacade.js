import { handleHttpErrors, makeOptions } from "../utils/fetchUtils";
import { jokeURL, } from "../utils/settings";

function jokeFetcher() {
  const fetchData = () => {
    const options = makeOptions("GET", true);
    return fetch(jokeURL, options).then(handleHttpErrors);
  };
  return { fetchData };
}
const facade = jokeFetcher();
export default facade;
