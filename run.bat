@ECHO OFF

ECHO.
ECHO.
ECHO.
ECHO Clear Channel Test. Welcome!

ECHO.
ECHO.
ECHO.
ECHO Maven Build (skipping tests because the database is not running yet) ...
ECHO.
call mvn clean install -DskipTests

ECHO.
ECHO.
ECHO.
ECHO Creating Docker containers ...
ECHO.
docker-compose up --build -d
TIMEOUT /T 30

ECHO.
ECHO.
ECHO.
ECHO Finally, testing it ...
ECHO.
call mvn surefire:test

ECHO.
ECHO.
ECHO.
ECHO Grand Finale ...
ECHO.
ECHO curl http://localhost:8080/cc_test/webresources/ping
ECHO.
curl http://localhost:8080/cc_test/webresources/ping

ECHO.
