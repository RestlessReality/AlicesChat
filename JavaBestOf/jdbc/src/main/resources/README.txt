################################################
#
# JDBC tutorial instructions
#
################################################

1. Unzip HeidiSQL_9.3_Portable.zip to H:\HeidiSQL_9.3_Portable
2. Unzip mariadb-10.1.16-winx64.zip to H:\mariadb-10.1.16-winx64
3. Run H:\mariadb-10.1.16-winx64\mariadb-10.1.16-winx64\bin>mysqld.exe --console
4. Start HeidiSQL H:\HeidiSQL_9.3_Portable\heidisql.exe
5. Connect with HeidiSQL to 127.0.0.1 and Port 3306 with user root
6. Create Database named example_db with UTF-16
7. Run Flyway Maven plugin to create the database schemas (mvn flyway:migrate)
8. Check with MariaDB whether the tables haven been created
9. Run the JdbcExampleApp
