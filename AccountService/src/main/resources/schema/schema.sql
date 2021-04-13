CREATE TABLE account
(
    id            VARCHAR PRIMARY KEY,
    customerId    VARCHAR,
    country       VARCHAR
)

CREATE TABLE balance
(
    id            SERIAL PRIMARY KEY,
    currency          VARCHAR,
    accountId    VARCHAR,
    amount       FLOAT
);
CREATE TABLE transaction
(
    id            VARCHAR PRIMARY KEY,
    accountId     VARCHAR,
    direction          VARCHAR,
    currency     VARCHAR,
    description     VARCHAR,
    amount     FLOAT,
    balanceAfterTransaction     FLOAT,
);