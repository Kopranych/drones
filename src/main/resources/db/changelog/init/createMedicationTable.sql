CREATE TABLE IF NOT EXISTS medication
(
    id
    bigint
    generated
    by
    default as
    identity,
    code
    varchar
(
    255
),
    image_url varchar
(
    255
),
    name varchar
(
    255
),
    weight numeric
(
    38,
    2
),
    primary key
(
    id
)
    );