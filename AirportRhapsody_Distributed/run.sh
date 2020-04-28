javac airportrhapsody/mainProgram/AirportRhapsody.java
for i in $(seq 1 20000)
do
	echo -e "\nRun n.o " $i
	java airportrhapsody.mainProgram.AirportRhapsody
done
