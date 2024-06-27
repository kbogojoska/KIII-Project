#!/bin/bash
set -e

docker-entrypoint.sh postgres &

until pg_isready -h localhost -p 5432 -U postgres; do
  echo "Waiting for PostgreSQL..."
  sleep 2
done

if psql -U postgres -d employeeDB -c '\dt' | grep -q 'No relations found.'; then
  echo "Initializing database..."
  psql -U postgres -d employeeDB -f /docker-entrypoint-initdb.d/init.sql
else
  echo "Database already initialized."
fi

wait
