CREATE TABLE IF NOT EXISTS drone
(
    serial_number    varchar(255) not null,
    battery_capacity numeric(38, 2),
    model            smallint,
    state            smallint,
    weight_limit     numeric(38, 2),
    medication_id    bigint,
    primary key (serial_number)
);