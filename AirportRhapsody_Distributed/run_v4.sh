#!/bin/bash/sh

for i in $(seq 1 2000)
do
	
	
	echo "STARTING Logger"
	java airportrhapsody.serverSide.Logger.ServerLogger &

	echo "STARTING Arrival Lounge"
	java airportrhapsody.serverSide.ArrivalLounge.ServerArrivalLounge &

	echo "STARTING Arr Term Exit"
	java airportrhapsody.serverSide.ArrTermExit.ServerArrTermExit &

	echo "STARTING Arr Trans Quay"
	java airportrhapsody.serverSide.ArrTransQuay.ServerArrTransQuay &

	echo "STARTING Coll Point"
	java airportrhapsody.serverSide.CollectionPoint.ServerCollectionPoint &

	echo "STARTING Dep Term Entrance"
	java airportrhapsody.serverSide.DepTermEntrance.ServerDepTermEntrance &

	echo "STARTING DepTransQuay"
	java airportrhapsody.serverSide.DepTransQuay.ServerDepTransQuay &

	echo "STARTING ReclaimOffice"
	java airportrhapsody.serverSide.ReclaimOffice.ServerReclaimOffice &

	echo "STARTING TempStorageArea"
	java airportrhapsody.serverSide.TempStorageArea.ServerTempStorageArea &

	echo "STARTING Porter"
	java airportrhapsody.clientSide.PorterMain &

	echo "STARTING BusDriver"
	java airportrhapsody.clientSide.BusDriverMain &

	echo "STARTING Passenger 0"
	java airportrhapsody.clientSide.PassengerMain 0 &

	echo "STARTING Passenger 1"
	java airportrhapsody.clientSide.PassengerMain 1 &

	echo "STARTING Passenger 2"
	java airportrhapsody.clientSide.PassengerMain 2 &

	echo "STARTING Passenger 3"
	java airportrhapsody.clientSide.PassengerMain 3 &

	echo "STARTING Passenger 4"
	java airportrhapsody.clientSide.PassengerMain 4 &

	echo "STARTING Passenger 5"
	java airportrhapsody.clientSide.PassengerMain 5 &

	# Code to kill the xterm process?
	wait

	echo "SIMULATION COMPLETE"

	sleep 1
done
