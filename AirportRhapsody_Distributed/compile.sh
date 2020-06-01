#!/bin/bash
#!/#!/usr/bin/env bash
gnome-terminal --title="Clean Class" --command="bash -c 'rm airportrhapsody/*.class; \
														rm airportrhapsody/*/*.class; \
														rm airportrhapsody/*/*/*.class; \
														$SHELL'" --tab --title="Compile" --command="bash -c 'javac airportrhapsody/*.java; javac airportrhapsody/clientSide/*.java; \
														javac airportrhapsody/serverSide/*.java; javac airportrhapsody/comInf/*.java; \
														javac airportrhapsody/serverSide/ArrivalLounge/*.java; \
														javac airportrhapsody/serverSide/ArrTermExit/*.java; \
														javac airportrhapsody/serverSide/ArrTransQuay/*.java; \
														javac airportrhapsody/serverSide/CollectionPoint/*.java; \
														javac airportrhapsody/serverSide/DepTermEntrance/*.java; \
														javac airportrhapsody/serverSide/DepTransQuay/*.java; \
														javac airportrhapsody/serverSide/Logger/*.java; \
														javac airportrhapsody/serverSide/ReclaimOffice/*.java; \
														javac airportrhapsody/serverSide/TempStorageArea/*.java; \
														$SHELL'"
