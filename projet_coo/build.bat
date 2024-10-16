@echo off
set BUILD=docs main test jar runjar

rem Commande principale
if "%1"=="" goto all
goto %1

:all
call :docs
call :main
goto :eof

:docs
javadoc -d docs -subpackages rentalsystem
goto :eof

:main
javac src\rentalsystem\*.java -d bin -Xlint:unchecked
goto :eof

:run
call :main
java -classpath bin rentalsystem.RentalSystemMain
goto :eof

:jar
call :main
jar cvfm rentalsystem.jar rentalsystem-manifest -C bin/ rentalsystem
goto :eof

:runjar
call :jar
java -jar rentalsystem.jar
goto :eof

:test
call :main
javac -classpath src -d bin test\rentalsystemtest\MockRearrange.java -Xlint:unchecked
javac -classpath src -d bin test\rentalsystemtest\MockWorker*.java
javac -classpath src -d bin test\rentalsystemtest\statestest\vehiclestatestest\MockVehicle.java
javac -classpath lib\*;bin -d bin test\rentalsystemtest\*.java
javac -classpath lib\*;bin -d bin test\rentalsystemtest\workertest\*.java
javac -classpath lib\*;bin -d bin test\rentalsystemtest\statestest\stationstatestest\*.java
javac -classpath lib\*;bin -d bin test\rentalsystemtest\statestest\vehiclestatestest\*.java
goto :eof

:runtest
call :test
java -jar lib\junit-platform-console-standalone-1.10.0.jar -classpath bin --select-class rentalsystemtest.BikeTest --select-class rentalsystemtest.ControlCenterTest --select-class rentalsystemtest.StationTest --select-class rentalsystemtest.ScooterTest --select-class rentalsystemtest.statestest.stationstatestest.EmptyStateTest --select-class rentalsystemtest.statestest.stationstatestest.FullStateTest --select-class rentalsystemtest.statestest.stationstatestest.MidStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.BrokenStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.IsOkStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.RentedStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.RepairingStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.StolenStateTest --select-class rentalsystemtest.workertest.RepairerTest
goto :eof

:clean
rmdir /s /q bin
rmdir /s /q docs
del /q *~
del /q *.jar
goto :eof

:eof