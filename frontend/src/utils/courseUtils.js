import courseFacade from "../facades/courseFacade"


export function addCourseMethod(courseName, description) {
    courseFacade.addCourse(courseName, description)
        .then((res) => {
            res.json()
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => console.log(e.detail))
            }
            else { console.log("Network error"); }
        })
}
