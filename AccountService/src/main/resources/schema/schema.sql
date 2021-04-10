CREATE TABLE account
(
  id            VARCHAR PRIMARY KEY,
  customerId    VARCHAR,
);

CREATE TABLE balance
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR,
    customerId    VARCHAR,
);

CREATE TABLE balance
(
    id            INT PRIMARY KEY auto_increment,
    name          VARCHAR,
    customerId    VARCHAR,
);