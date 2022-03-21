#!/bin/bash

wait_for_cassandra() {
  echo "Waiting for Cassandra"
  sleep 2
}

until echo "$(< schema.cql)" | cqlsh;
do
  wait_for_cassandra
done &

exec /docker-entrypoint.sh "$@"

#when using cassandra version above 4 in dockerfile, use this to find docker-entrypoint file
#exec /usr/local/bin/docker-entrypoint.sh "$@"