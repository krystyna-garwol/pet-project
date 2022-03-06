#!/bin/bash

wait_for_cassandra() {
  echo "Waiting for Cassandra"
  sleep 2
}

until echo "$(< schema.cql)" | cqlsh;
do
  wait_for_cassandra
done &

exec "$@"