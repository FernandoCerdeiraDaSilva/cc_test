@ECHO OFF
ECHO Stopping containers...
docker-compose down
docker-compose stop
docker-compose rm
docker rmi -f cc_test_cc_tomcat
docker rmi -f cc_tomcat
ECHO done!
ECHO.
