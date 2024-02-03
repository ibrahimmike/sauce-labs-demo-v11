#!/bin/bash

echo "=============================================================================="
echo "Hub-Host    : ${HUB-HOST:-hub}"
echo "BROWSER     : ${BROWSER:-chrome}"
echo "THREAD-COUNT: ${THREAD_COUNT:-4}"
echo "TEST-SUITE  : ${TEST_SUITE:-regression-test.xml}"
echo "=============================================================================="


count=0
while [ "$( curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready )" != "true" ]

  do
    count=$((count+1))
    echo "Attempt number :  ${count}"

    sleep 1
    if [ $count -ge 30 ]

     then
       echo "The time elapsed 30 seconds And exited"
      exit;
    fi
done

java -Dselenium.grid.hubHost="${HUB_HOST:-selenium-hub}" \
     -DrunMode=remote \
     -Dbrowser="${BROWSER:-BROWSER}" \
     -cp 'libs/*'\
     org.testng.TestNG \
     test-suites/"${TEST_SUITE}"

