#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/envs.sh

echo
echo stopping ${APP_NAME}...

kill $(cat ${SCRIPT_ABS_PATH}/pid.file)
