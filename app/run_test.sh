#!/bin/sh

command=assembleIntegrationDebug

case ${CIRCLE_BRANCH} in
master)
  command=assembleStagingDebug
  ;;
esac

echo ./gradlew clean ${command}
./gradlew clean ${command}
