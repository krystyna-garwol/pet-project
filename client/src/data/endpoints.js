export const services = {
  "purchase-service": {
    env: "http://localhost:8081",
    request: {
      GET: ["/private/status", "/private/metrics"],
      POST: ["/private/purchase"],
    },
  },
  "order-service": {
    env: "http://localhost:8082",
    request: {
      GET: ["/private/status", "/private/metrics"],
    },
  },
  "inventory-service": {
    env: "http://localhost:8083",
    request: {
      GET: ["/private/status", "/private/metrics"],
    },
  },
};
