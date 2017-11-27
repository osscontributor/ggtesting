#!/bin/bash
set -e
cd 3.2.x
./gradlew clean check --stacktrace
cd ../3.3.x
./gradlew clean check --stacktrace

EXIT_STATUS=0

echo "Finished build with status: $EXIT_STATUS"

exit $EXIT_STATUS
