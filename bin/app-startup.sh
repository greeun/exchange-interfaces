#!/bin/sh
SCRIPT_ABS_PATH=$(cd "$(dirname "$0")" && pwd)
. ${SCRIPT_ABS_PATH}/envs.sh

echo
echo running ${APP_NAME}...

java ${JVM_OPTS} ${APP_OPTS} & echo $! > ${SCRIPT_ABS_PATH}/pid.file &
