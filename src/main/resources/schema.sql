CREATE TABLE IF NOT EXISTS application_clock (
    time_zone VARCHAR(20) NOT NULL,
    base_time DATETIME NOT NULL,
    PRIMARY KEY (time_zone, clock)
);
