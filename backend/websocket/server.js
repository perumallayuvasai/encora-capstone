const express = require("express");
const { WebSocketServer } = require("ws");

const app = express();
const PORT = 3000;

app.use(express.json());

app.get("/", (req, res) => {
  res.send("Backend with WebSocket is up!");
});

const server = app.listen(PORT, () => {
  console.log(`Server running on http://localhost:${PORT}`);
});

// Create WebSocket server using the same HTTP server
const wss = new WebSocketServer({ server });

wss.on("connection", (ws) => {
  console.log("WebSocket connection established");

  ws.on("message", (message) => {
    console.log("received:", message);
    // Echo message back to client
    ws.send(`Server says: ${message}`);
  });

  ws.on("close", () => {
    console.log("WebSocket connection closed");
  });
});
