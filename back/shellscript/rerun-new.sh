#!/bin/sh
PWD="$(pwd -LP)"
FIRST_PWD="$PWD"

BUILD_FLAG=true

while true; do
  read -p "Do you wish to build? (y/n) " yn
  case $yn in
  [Yy]*)
    BUILD_FLAG=true
    break
    ;;
  [Nn]*)
    BUILD_FLAG=false
    break
    ;;
  *) echo "Please answer yes or no." ;;
  esac
done

if $BUILD_FLAG; then
  echo "start clean build"
  {
    # shellcheck disable=SC2164
    cd ${FIRST_PWD}
    cd ..
    ./gradlew clean build --stacktrace --debug
    cd build/libs
    mv demo-0.0.1-SNAPSHOT.jar ../../
  }

  echo -e "build done\n\n"
fi

{
  # shellcheck disable=SC2164
  cd ${FIRST_PWD}
  bash kill.sh
}

echo "start running"
{
  # shellcheck disable=SC2164

  cd ..
  sudo nohup /usr/bin/java -jar demo-0.0.1-SNAPSHOT.jar 1>/dev/null 2>/dev/null &
}

echo -e "done\n"
