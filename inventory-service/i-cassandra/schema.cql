CREATE KEYSPACE IF NOT EXISTS inventory WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

CREATE TABLE IF NOT EXISTS inventory.stock_item (
    name text,
    animal text,
    inStock int,
    price double,
    type text,
    PRIMARY KEY (name, animal)
);

INSERT INTO inventory.stock_item (name, animal, inStock, price, type)VALUES('Purina', 'cat', 50, 5.50, 'food');
INSERT INTO inventory.stock_item (name, animal, inStock, price, type)VALUES('Whiskas', 'cat', 30, 3.50, 'food');
INSERT INTO inventory.stock_item (name, animal, inStock, price, type)VALUES('Pedigree', 'dog', 40, 4.99, 'food');