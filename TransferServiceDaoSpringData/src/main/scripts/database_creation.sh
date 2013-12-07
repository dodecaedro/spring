#!/bin/bash

psql -U postgres -h develvm -f 00_role_database.sql
psql -U postgres -h develvm -d transferdb -f 01_schemas.sql
psql -U postgres -h develvm -d transferdb -f 02_data.sql