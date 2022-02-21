import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { services } from "../data/endpoints";
import { sendGetRequest } from "../utils/requests";

const DemoForm = () => {
  const [input, setInput] = useState({
    service: "purchase-service",
    requestType: "GET",
    endpoint: "/private/status",
  });
  const [response, setResponse] = useState();

  const getUrl = (service) => {
    return services[service].env + input.endpoint;
  };

  const handleChange = (event) => {
    if (input.service === "purchase-service" && event.target.value === "POST") {
      setInput({
        ...input,
        [event.target.name]: event.target.value,
        endpoint: "/private/purchase",
      });
    } else if (
      input.service === "purchase-service" &&
      event.target.value === "GET"
    ) {
      setInput({
        ...input,
        [event.target.name]: event.target.value,
        endpoint: "/private/status",
      });
    } else {
      setInput({ ...input, [event.target.name]: event.target.value });
    }
  };

  const handleServiceChange = (event) => {
    setInput({
      ...input,
      [event.target.name]: event.target.value,
      requestType: "GET",
      endpoint: "/private/status",
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    sendGetRequest(getUrl(input.service), setResponse);
  };

  return (
    <Container className="demo-form">
      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-4">
          <Row>
            <Col lg="4" md="6" className="mb-4">
              <Form.Label>Service</Form.Label>
              <Form.Select
                onChange={handleServiceChange}
                name="service"
                value={input.service}
              >
                {Object.keys(services).map((service, index) => {
                  return <option key={index}>{service}</option>;
                })}
              </Form.Select>
            </Col>
            <Col lg="4" md="6" className="mb-4">
              <Form.Label>Request Type</Form.Label>
              <Form.Select
                onChange={handleChange}
                name="requestType"
                value={input.requestType}
              >
                {Object.entries(services[input.service].request).map(
                  (reqType, index) => {
                    return <option key={index}>{reqType[0]}</option>;
                  }
                )}
              </Form.Select>
            </Col>
            <Col lg="4" md="6">
              <Form.Label>Endpoint</Form.Label>
              <Form.Select
                onChange={handleChange}
                name="endpoint"
                value={input.endpoint}
              >
                {Object.entries(services[input.service])[1][1][
                  input.requestType
                ].map((endpoint, index) => {
                  return <option key={index}>{endpoint}</option>;
                })}
              </Form.Select>
            </Col>
          </Row>
        </Form.Group>
        <Form.Group>
          <Form.Label>Request Body</Form.Label>
          <Form.Control as="textarea" rows={8} />
        </Form.Group>
        <div className="btn-wrapper">
          <Button type="submit" variant="secondary" className="btn">
            Submit
          </Button>
        </div>
      </Form>
      <div className="response">
        <p>Response Body</p>
        <div className="response-area">{response}</div>
      </div>
    </Container>
  );
};

export default DemoForm;
