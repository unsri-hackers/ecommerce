CREATE TABLE IF NOT EXISTS inventories (
  id INT NOT NULL,
  item_name VARCHAR(250) NOT NULL,
  price DOUBLE NOT NULL,
  PRIMARY KEY (id)
);