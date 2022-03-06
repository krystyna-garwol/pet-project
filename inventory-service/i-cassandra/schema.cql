CREATE KEYSPACE IF NOT EXISTS inventory WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

CREATE TABLE IF NOT EXISTS inventory.stock_item (
    name text,
    animal text,
    inStock int,
    price double,
    type text,
    PRIMARY KEY (name, animal)
);