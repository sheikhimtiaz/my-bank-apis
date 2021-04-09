CREATE TABLE account
(
  id            INT PRIMARY KEY auto_increment,
  customerId    VARCHAR,
);

CREATE TABLE balance
(
    id            INT PRIMARY KEY auto_increment,
    name          VARCHAR,
    customerId    VARCHAR,
);