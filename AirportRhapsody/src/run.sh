javac airportrhapsody/mainProgram/AirportRhapsody.java
for i in $(seq 1 200)
do
	echo -e "\nRun n.o " $i
	java airportrhapsody.mainProgram.AirportRhapsody
done
