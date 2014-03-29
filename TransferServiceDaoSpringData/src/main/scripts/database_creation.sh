#!/bin/bash

psql --username postgres --host develvm --file 00_role_database.sql
psql --username postgres --host develvm --file 01_database.sql
psql --username postgres --host develvm --file 02_schemas.sql --dbname transferdb
psql --username postgres --host develvm --file 03_data.sql --dbname transferdb