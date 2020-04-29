javac airportrhapsody/*/*.java
for i in $(seq 1 2000)
do
	echo -e "\nRun n.o " $i
	java airportrhapsody.mainProgram.AirportRhapsody
done
