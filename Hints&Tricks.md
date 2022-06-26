# Concert Demos Hints & Tricks

This is listing that were useful in the making of this project:


## Hints & Tricks

-   Fix PostgreSQL:

```shell script
sudo chown -R postgres:postgres /var/run/postgresql
```

-   Find Postgres configuration file:

```shell script
psql -U postgres -c 'SHOW config_file'
```

-   Change connections properties:

```properties
max_connections=1000
shared_buffers=512MB
```

-   Restart Postgresql

```shell script
service postgresql restart
```

-   Possible postgres locations:

```text
/etc/postgresql/9.5/main/postgresql.conf
/var/lib/pgsql/data/postgresql.conf
```

-   Start docker machine

```shell script
service docker start
```

-   Git tag change

```bash
git tag new-tag old-tag
git tag -d old-tag
git push origin :refs/tags/old-tag
git push --tags
git pull --prune --tags
```
