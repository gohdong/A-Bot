#!/bin/bash
PWD="$(pwd)"

echo "start clean build"
{
  cd ..
  ./gradlew clean build --stacktrace --debug
}
echo -e "build done\n\n"

{
  # shellcheck disable=SC2164
  cd ./shellscript
  bash kill.sh
}


echo "start running"
{
  # shellcheck disable=SC2164
  cd ../build/libs
  mv demo-0.0.1-SNAPSHOT.jar ../../
  cd ../..
  sudo nohup /usr/bin/java -jar demo-0.0.1-SNAPSHOT.jar 1>/dev/null 2>/dev/null &
}

echo -e "done\n"