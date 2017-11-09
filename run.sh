#!/bin/bash
CLASSPATH=.:approximateClient-1.0-SNAPSHOT.jar
JAVA_OPTS="-Xmx4g -Xms4g"
NO_OF_EVENTS=100000000
SERVER_IP=localhost

java -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=settings=profile,duration=1800s,dumponexit=true,filename=approximate-client-test.jfr $JAVA_OPTS -cp $CLASSPATH -DnoOfEvents=$NO_OF_EVENTS -Dip=$SERVER_IP Main
