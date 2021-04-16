FROM mariadb:10.5.8
ENV MYSQL_ROOT_PASSWORD=capablanca
COPY schemas.sql /docker-entrypoint-initdb.d/
EXPOSE 3306