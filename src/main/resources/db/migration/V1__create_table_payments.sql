CREATE TABLE IF NOT EXISTS payments (
  id VARCHAR(255) NOT NULL,
  active BOOLEAN,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  price DOUBLE PRECISION NOT NULL CHECK (price > 0),
  status VARCHAR(255) NOT NULL,
  method VARCHAR(255) NOT NULL,
  order_id VARCHAR(255) NOT NULL,
  customer_id VARCHAR(255) NOT NULL,

  PRIMARY KEY (id)
);
