-- CREATE TABLE  test
-- (
--     username varchar(45) NOT NULL PRIMARY KEY ,
--     password varchar(450) NOT NULL,
--     enabled integer NOT NULL DEFAULT '1'
-- )

CREATE TABLE [ IF NOT EXISTS ] IF NOT EXISTS
(
    id VARCHAR ,
);


CREATE TABLE account
(
  id            VARCHAR PRIMARY KEY,
  customerId    VARCHAR,
  account       VARCHAR
);

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