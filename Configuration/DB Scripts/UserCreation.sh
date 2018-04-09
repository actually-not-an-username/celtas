#!/bin/bash
echo "========================================================"
echo "      NOTE: before runing this script you have to       "
echo "make yourself sure of download and installing PostgreSQL"
echo " this script gonna create the celtas DB user and Schema "
echo "========================================================"
sudo -u postgres createuser celta
sudo -u postgres createdb celtas
psql -U postgres -d postgres -c "alter user celta with encrypted password 'Adm1nP4$$w0rd';"
psql -U postgres -d postgres -c "grant all privileges on database celtas to celta;"
echo "========================================================"
echo "          user and db script executed correctly         "
echo "========================================================"