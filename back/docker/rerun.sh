#!/bin/bash

echo "BUILD"
{
  cd ..
  ./gradlew clean build --stacktrace --debug
}
echo -e "BUILD DONE\n\n"

echo "KILL PROCESS IF EXISTS"
{
  cd ..
  ./gradlew clean build --stacktrace --debug
}
echo -e "BUILD done\n\n"

PWD="$(pwd)"
echo "RUN SPRING"
{
  # shellcheck disable=SC2164
  cd ${PWD}/build/libs
  java -jar demo-0.0.1-SNAPSHOT.jar
}

echo -e "DONE\n"