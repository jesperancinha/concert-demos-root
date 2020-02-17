FROM postgres:12

COPY init-dbs.sh /docker-entrypoint-initdb.d/

COPY postgresql.conf /etc/postgresql/postgresql.conf

EXPOSE 5432
