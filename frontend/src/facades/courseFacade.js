import { makeOptions, handleHttpErrors } from "../utils/fetchUtils";
import { baseURL } from "../utils/settings"

function courseFacade() {

    const addCourse = (courseName, description) => {
        const options = makeOptions("POST", true, {
            courseName: courseName,
            description: description,
        });
        return fetch(baseURL + "/course/add", options)
            .then(handleHttpErrors)
    };







    return {
        addCourse,
    }
}

const facade = courseFacade();
export default facade;