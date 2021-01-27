import facade from "../facades/fetchFacade";
import React, { useState, useEffect } from "react";
import { Jumbotron, Row, Col, Form } from "react-bootstrap";

export default function Admin({ addCourse }) {
    const init = { courseName: "", description: "" };
    const [course, setCourse] = useState(init);
    const [error, setError] = useState("");


    const makeCourse = (evt) => {
        evt.preventDefault();
        addCourse(course.courseName, course.description)
    }

    const onChange = (evt) => {
        setCourse({
            ...course,
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
                <Col></Col>
            </Row>
        </div>
    );
}
