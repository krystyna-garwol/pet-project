CREATE KEYSPACE IF NOT EXISTS inventory WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1 };

CREATE TABLE IF NOT EXISTS inventory.product (
    productId text,
    name text,
    animal text,
    stock int,
    price double,
    type text,
    PRIMARY KEY (productId)
);

INSERT INTO inventory.product (productId, name, animal, stock, price, type)VALUES('1', 'Purina', 'cat', 50, 5.50, 'food');
INSERT INTO inventory.product (productId, name, animal, stock, price, type)VALUES('2', 'Whiskas', 'cat', 30, 3.50, 'food');
INSERT INTO inventory.product (productId, name, animal, stock, price, type)VALUES('3', 'Pedigree', 'dog', 40, 4.99, 'food');