import courseFacade from "../facades/courseFacade"


export function addCourseMethod(courseName, description, setError) {
    courseFacade.addCourse(courseName, description)
        .then((res) => {
            res.json()
            console.log(res)
            setError(res)
        })
        .catch(err => {
            if (err.status) {
                err.fullError.then(e => {
                    console.log(e.detail)
                    setError(e.message)
                })
            }
            else { console.log("Network error"); }
        })
}
