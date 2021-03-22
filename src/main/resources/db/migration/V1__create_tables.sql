CREATE TABLE IF NOT EXISTS event_result (
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `competitor` varchar(100) NOT NULL,
    `event_type` varchar(3) NOT NULL,
    `score` decimal(10,2) NOT NULL,
    `points` int NOT NULL
);