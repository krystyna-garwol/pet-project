#!/bin/bash

CASSANDRA_KEYSPACE="inventory"
TABLE="stock_item"
CQL=`cat createTables.cql`

if [[ ! -z "$CASSANDRA_KEYSPACE" && $1 = 'cassandra' ]];
then
  CQL="
  CREATE KEYSPACE $CASSANDRA_KEYSPACE WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};
  USE $CASSANDRA_KEYSPACE;
  CREATE TABLE $TABLE(name text, animal text, inStock int, price double, type text, PRIMARY KEY (name, animal));
  INSERT INTO inventory.stock_item (name, animal, inStock, price, type) VALUES('Purina', 'cat', 50, 3.50, 'cat');
  "

  until echo $CQL | cqlsh;
  do
    echo "Waiting for Cassandra"
    sleep 2
  done &
fi

exec "$@"