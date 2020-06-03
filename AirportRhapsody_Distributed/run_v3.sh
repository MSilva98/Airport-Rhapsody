#!/bin/bash/sh

for i in $(seq 1 2000)
do
	
	echo "STARTING Logger"

	    xterm -e java airportrhapsody.serverSide.Logger.ServerLogger &

	echo "STARTING Arrival Lounge"

	    xterm -e java airportrhapsody.serverSide.ArrivalLounge.ServerArrivalLounge &

	echo "STARTING Arr Term Exit"

	    xterm -e java airportrhapsody.serverSide.ArrTermExit.ServerArrTermExit &

	echo "STARTING Arr Trans Quay"

	    xterm -e java airportrhapsody.serverSide.ArrTransQuay.ServerArrTransQuay &

	echo "STARTING Coll Point"

	    xterm -e java airportrhapsody.serverSide.CollectionPoint.ServerCollectionPoint &

	echo "STARTING Dep Term Entrance"

	    xterm -e java airportrhapsody.serverSide.DepTermEntrance.ServerDepTermEntrance &

	echo "STARTING DepTransQuay"

	    xterm -e java airportrhapsody.serverSide.DepTransQuay.ServerDepTransQuay &

	echo "STARTING ReclaimOffice"

	    xterm -e java airportrhapsody.serverSide.ReclaimOffice.ServerReclaimOffice &

	echo "STARTING TempStorageArea"

	    xterm -e java airportrhapsody.serverSide.TempStorageArea.ServerTempStorageArea &

	echo "STARTING Porter"

	    xterm -e java airportrhapsody.clientSide.PorterMain &

	echo "STARTING BusDriver"

	    xterm -e java airportrhapsody.clientSide.BusDriverMain &

	echo "STARTING Passenger 0"

	    xterm -e java airportrhapsody.clientSide.PassengerMain 0 &

	echo "STARTING Passenger 1"

	    xterm -e java airportrhapsody.clientSide.PassengerMain 1 &

	echo "STARTING Passenger 2"

	    xterm -e java airportrhapsody.clientSide.PassengerMain 2 &

	echo "STARTING Passenger 3"

	    xterm -e java airportrhapsody.clientSide.PassengerMain 3 &

	echo "STARTING Passenger 4"

	    xterm -e java airportrhapsody.clientSide.PassengerMain 4 &

	echo "STARTING Passenger 5"

	    xterm -e java airportrhapsody.clientSide.PassengerMain 5 &

	# Code to kill the xterm process?
	wait

	echo "SIMULATION COMPLETE"
done
