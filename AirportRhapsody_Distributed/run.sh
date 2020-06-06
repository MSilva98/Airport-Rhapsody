#!/#!/usr/bin/env bash
gnome-terminal					--tab --title="Logger" -- bash -c "java airportrhapsody.serverSide.Logger.ServerLogger ;"
gnome-terminal					--tab --title="Arrival Lounge" -- bash -c "java airportrhapsody.serverSide.ArrivalLounge.ServerArrivalLounge ; "
gnome-terminal					--tab --title="Arr Term Exit" -- bash -c 'java airportrhapsody.serverSide.ArrTermExit.ServerArrTermExit ; '
gnome-terminal					--tab --title="Arr Trans Quay" -- bash -c 'java airportrhapsody.serverSide.ArrTransQuay.ServerArrTransQuay ; '
gnome-terminal					--tab --title="Coll Point" -- bash -c 'java airportrhapsody.serverSide.CollectionPoint.ServerCollectionPoint ; '
gnome-terminal					--tab --title="Dep Term Entrance" -- bash -c 'java airportrhapsody.serverSide.DepTermEntrance.ServerDepTermEntrance ; '
gnome-terminal					--tab --title="DepTransQuay" -- bash -c 'java airportrhapsody.serverSide.DepTransQuay.ServerDepTransQuay ; '
gnome-terminal					--tab --title="ReclaimOffice" -- bash -c 'java airportrhapsody.serverSide.ReclaimOffice.ServerReclaimOffice ; '
gnome-terminal					--tab --title="TempStorageArea" -- bash -c 'java airportrhapsody.serverSide.TempStorageArea.ServerTempStorageArea ; '
gnome-terminal					--tab --title="Porter" -- bash -c 'java airportrhapsody.clientSide.PorterMain ; '
gnome-terminal					--tab --title="BusDriver" -- bash -c 'java airportrhapsody.clientSide.BusDriverMain ; '
gnome-terminal					--tab --title="Passenger 0" -- bash -c 'java airportrhapsody.clientSide.PassengerMain 0 ; '
gnome-terminal					--tab --title="Passenger 1" -- bash -c 'java airportrhapsody.clientSide.PassengerMain 1 ; '
gnome-terminal					--tab --title="Passenger 2" -- bash -c 'java airportrhapsody.clientSide.PassengerMain 2 ; '
gnome-terminal					--tab --title="Passenger 3" -- bash -c 'java airportrhapsody.clientSide.PassengerMain 3 ; '
gnome-terminal					--tab --title="Passenger 4" -- bash -c 'java airportrhapsody.clientSide.PassengerMain 4 ; '
gnome-terminal					--tab --title="Passenger 5" -- bash -c 'java airportrhapsody.clientSide.PassengerMain 5 ;'


