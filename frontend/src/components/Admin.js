
import React, { useState, useEffect } from "react";
import { Jumbotron, Row, Col, Form } from "react-bootstrap";

export default function Admin({ addCourse, addClass }) {
    const init = { courseName: "", description: "" };
    const [course, setCourse] = useState(init);
    const [error, setError] = useState("");
    const classInit = { courseName2: "", semester: "", numberOfStudents: 0 }
    const [classe, setClass] = useState(classInit);

    const makeCourse = (evt) => {
        evt.preventDefault();
        addCourse(course.courseName, course.description, setError)
    }

    const onChange = (evt) => {
        setCourse({
            ...course,
            [evt.target.id]: evt.target.value,
        });
        setError("")
    };



    const makeClass = (evt) => {
        evt.preventDefault();
        addClass(classe)
    }
    const onChangeClass = (evt) => {
        setClass({
            ...classe,
            [evt.target.id]: evt.target.value,
        });
    };
    return (
        <div className="text-center w-100">
            <Row>
                <Col></Col>
                <Col>
                    <Jumbotron className="mt-2 text-center">
                        <h2>Create new Course</h2>
                        <Form.Group onChange={onChange}>
                            <Form.Label>Course name</Form.Label>
                            <Form.Control id="courseName" type="name" placeholder="Enter new course name" />
                            <Form.Label>Course description</Form.Label>
                            <Form.Control id="description" type="name" placeholder="Enter description of course" />
                            <button className="btn btn-primary m-2" onClick={makeCourse}>
                                Make new course
                            </button>
                        </Form.Group>
                        <p>{error}</p>
                    </Jumbotron>

                </Col>
                <Col>
                <Jumbotron className="mt-2 text-center">
                    <h2>Add a class to a course</h2>
                    <Form.Group onChange={onChangeClass}>
                        <Form.Label>Course name</Form.Label>
                        <Form.Control id="courseName2" type="text" placeholder="Enter an existing course name" />
                        <Form.Label>Semester</Form.Label>
                        <Form.Control id="semester" type="text" placeholder="Enter semester" />
                        <Form.Label>Number of students</Form.Label>
                        <Form.Control id="numberOfStudents" type="number" placeholder="Enter how many students in class" />
                        <button className="btn btn-primary m-2" onClick={makeClass}>
                            Make new class
                            </button>
                    </Form.Group>

                </Jumbotron>
                </Col>
            </Row>
        </div>
    );
}
