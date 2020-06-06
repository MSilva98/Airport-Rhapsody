#!/bin/bash
#!/bin/bash/sh

#Auxilio do grupo P2G6 para a realização desta parte so script
for i in $(seq 1 2000)
do
	echo -e "\nRun n.o " $i
	sh run.sh 
	wait
	while [ $(ps -aux | grep airportrhapsody | wc -l) -gt 1 ]; do
	ps -aux | grep airportrhapsody | wc -l
        sleep 1
        echo "Airport Active"
    done
done

