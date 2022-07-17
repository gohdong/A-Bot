#!/bin/bash

echo "kill port '8080' before start this spring app"
PID=$(sudo lsof -ti :8080)
if [ -z "${PID}" ] ; then
   echo -e "Nothing to kill\n\n"
else
   echo -e "8080 Process killed\n\n"
   sudo kill -9 ${PID}
fi