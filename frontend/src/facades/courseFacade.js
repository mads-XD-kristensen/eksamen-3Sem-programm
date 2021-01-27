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


    const showCourses = () => {
        const options = makeOptions("GET", true);
        return fetch(baseURL + "/course/all", options).then(handleHttpErrors)
    }



    return {
        addCourse,
        showCourses,
    }
}

const facade = courseFacade();
export default facade;