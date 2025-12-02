const WebSocket = require("ws");
const { Kafka } = require("kafkajs");

// --- CONFIGURATION ---
const KAFKA_BROKER = "localhost:9092";
const TOPIC = "order-notification-topic";
const WS_PORT = 8081;

// --- 1. WEBSOCKET SERVER SETUP ---
const wss = new WebSocket.Server({ port: WS_PORT });
const clients = new Map(); // Store clients: userId -> WebSocket Connection

console.log(`WebSocket server started on port ${WS_PORT}`);

wss.on("connection", (ws, req) => {
    // EXTRACT USER ID FROM HEADERS
    // Headers are lowercased by Node.js automatically
    const userId = req.headers["x-user-id"];

    if (!userId) {
        console.log("Connection rejected: No x-user-id header provided");
        ws.close(1008, "UserId required in headers"); // 1008 = Policy Violation
        return;
    }

    // Store the connection
    clients.set(userId, ws);
    console.log(
        `User connected: ${userId} (Roles: ${req.headers["x-user-roles"] || "N/A"})`,
    );

    ws.on("close", () => {
        clients.delete(userId);
        console.log(`User disconnected: ${userId}`);
    });

    ws.on("error", (err) => {
        console.error(`WebSocket error for user ${userId}:`, err);
    });
});

// --- 2. KAFKA CONSUMER SETUP ---
const kafka = new Kafka({
    clientId: "notification-service",
    brokers: [KAFKA_BROKER],
});

const consumer = kafka.consumer({ groupId: "notification-group" });

const runKafkaConsumer = async () => {
    await consumer.connect();
    console.log("Kafka Consumer connected");

    await consumer.subscribe({ topic: TOPIC, fromBeginning: false });

    await consumer.run({
        eachMessage: async ({ topic, partition, message }) => {
            try {
                const event = JSON.parse(message.value.toString());
                const targetUserId = event.userId;

                console.log(
                    `Received notification for User: ${targetUserId}, Status: ${event.status}`,
                );

                // --- 3. PUSH TO USER ---
                const clientWs = clients.get(targetUserId);

                if (clientWs && clientWs.readyState === WebSocket.OPEN) {
                    clientWs.send(JSON.stringify(event));
                    console.log(`Notification pushed to User: ${targetUserId}`);
                } else {
                    // Optional: Store offline notification to DB here if needed
                    console.log(
                        `User ${targetUserId} offline. Notification skipped.`,
                    );
                }
            } catch (error) {
                console.error("Error processing Kafka message:", error);
            }
        },
    });
};

runKafkaConsumer().catch(console.error);
