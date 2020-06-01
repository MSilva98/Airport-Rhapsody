#!/#!/usr/bin/env bash
gnome-terminal					--tab --title="Logger" --command="bash -c 'java airportrhapsody.serverSide.Logger.ServerLogger ; $SHELL'"
gnome-terminal					--tab --title="Arrival Lounge" --command="bash -c 'java airportrhapsody.serverSide.ArrivalLounge.ServerArrivalLounge ; $SHELL'"
gnome-terminal					--tab --title="Arr Term Exit" --command="bash -c 'java airportrhapsody.serverSide.ArrTermExit.ServerArrTermExit ; $SHELL'"
gnome-terminal					--tab --title="Arr Trans Quay" --command="bash -c 'java airportrhapsody.serverSide.ArrTransQuay.ServerArrTransQuay ; $SHELL'"
gnome-terminal					--tab --title="Coll Point" --command="bash -c 'java airportrhapsody.serverSide.CollectionPoint.ServerCollectionPoint ; $SHELL'"
gnome-terminal					--tab --title="Dep Term Entrance" --command="bash -c 'java airportrhapsody.serverSide.DepTermEntrance.ServerDepTermEntrance ; $SHELL'"
gnome-terminal					--tab --title="DepTransQuay" --command="bash -c 'java airportrhapsody.serverSide.DepTransQuay.ServerDepTransQuay ; $SHELL'"
gnome-terminal					--tab --title="ReclaimOffice" --command="bash -c 'java airportrhapsody.serverSide.ReclaimOffice.ServerReclaimOffice ; $SHELL'"
gnome-terminal					--tab --title="TempStorageArea" --command="bash -c 'java airportrhapsody.serverSide.TempStorageArea.ServerTempStorageArea ; $SHELL'"
gnome-terminal					--tab --title="Porter" --command="bash -c 'java airportrhapsody.clientSide.PorterMain ; $SHELL'"
gnome-terminal					--tab --title="BusDriver" --command="bash -c 'java airportrhapsody.clientSide.BusDriverMain ; $SHELL'"
gnome-terminal					--tab --title="Passenger 0" --command="bash -c 'java airportrhapsody.clientSide.PassengerMain 0 ; $SHELL'"
gnome-terminal					--tab --title="Passenger 1" --command="bash -c 'java airportrhapsody.clientSide.PassengerMain 1 ; $SHELL'"
gnome-terminal					--tab --title="Passenger 2" --command="bash -c 'java airportrhapsody.clientSide.PassengerMain 2 ; $SHELL'"
gnome-terminal					--tab --title="Passenger 3" --command="bash -c 'java airportrhapsody.clientSide.PassengerMain 3 ; $SHELL'"
gnome-terminal					--tab --title="Passenger 4" --command="bash -c 'java airportrhapsody.clientSide.PassengerMain 4 ; $SHELL'"
gnome-terminal					--tab --title="Passenger 5" --command="bash -c 'java airportrhapsody.clientSide.PassengerMain 5 '"
