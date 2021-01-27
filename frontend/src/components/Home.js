import React, { useState, useEffect } from "react";
import facade from "../facades/courseFacade"

export default function Home() {
  const [allCourses, setAllCourses] = useState({ isEmpty: true });

  useEffect(() => {
    facade.showCourses().then((data) => {
      console.log(data)
      setAllCourses(data)
    })
  }, [])

  return (
    <div className="text-center w-100">
      <h1>All courses</h1>
      {allCourses.isEmpty ? (
        <p>Loading...</p>
      ) : (
          <table>
            <thead><tr>
              <td>Course name</td>
              <td>Description</td>

            </tr></thead>
            <tbody>
              {allCourses.map((course) => {
                return (
                  <tr key={course.courseName}>
                    <td>{course.courseName}</td>
                    <td>{course.description}</td>
                  </tr>
                )
              })}
            </tbody>
          </table>

        )}



    </div>
  );
}
