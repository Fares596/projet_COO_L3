BUILD = docs main test jar runjar

all: $(BUILD)

docs:	
	javadoc -d docs -subpackages rentalsystem

main:
	javac src/rentalsystem/*.java -d bin -Xlint:unchecked

run: main
	java rentalsystem.RentalSystemMain

jar: main
	jar cvfm rentalsystem.jar rentalsystem-manifest -C bin/ rentalsystem

runjar: jar
	java -jar rentalsystem.jar

test: main
	javac -classpath src -d bin test/rentalsystemtest/MockRearrange.java -Xlint:unchecked
	javac -classpath src -d bin test/rentalsystemtest/MockWorker*.java
	javac -classpath src -d bin test/rentalsystemtest/statestest/vehiclestatestest/MockVehicle.java
	javac -classpath lib/*:bin -d bin test/rentalsystemtest/*.java
	javac -classpath lib/*:bin -d bin test/rentalsystemtest/workertest/*.java
	javac -classpath lib/*:bin -d bin test/rentalsystemtest/statestest/stationstatestest/*.java
	javac -classpath lib/*:bin -d bin test/rentalsystemtest/statestest/vehiclestatestest/*.java

runtest: test
	java -jar lib/junit-platform-console-standalone-1.10.0.jar -classpath bin --select-class rentalsystemtest.BikeTest --select-class rentalsystemtest.ControlCenterTest --select-class rentalsystemtest.StationTest --select-class rentalsystemtest.ScooterTest --select-class rentalsystemtest.statestest.stationstatestest.EmptyStateTest --select-class rentalsystemtest.statestest.stationstatestest.FullStateTest --select-class rentalsystemtest.statestest.stationstatestest.MidStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.BrokenStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.IsOkStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.RentedStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.RepairingStateTest --select-class rentalsystemtest.statestest.vehiclestatestest.StolenStateTest --select-class rentalsystemtest.workertest.RepairerTest

clean:
	rm -rf bin/
	rm -rf docs/
	rm -f *~
	rm -f *.jar

.PHONY: clean
