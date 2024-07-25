#!/bin/sh
APP_NAME=exchange-interfaces
VERSION=0.5.0sb
APP_PATH=..
LIB_FILE_NAME=${APP_NAME}-${VERSION}.jar
CONFIG_FILE_NAME=application.yml

# library file(.jar) path
LIB_FILE_PATH=${APP_PATH}/libs/${LIB_FILE_NAME}
# config file path
CONFIG_FILE_PATH=${APP_PATH}/config/${CONFIG_FILE_NAME}

## JVM memory options
JVM_HEAP_OPTS="-Xms512M -Xmx512M"

## JVM performance options
JVM_PERFORMANCE_OPTS="-server -XX:+UseG1GC -XX:MaxGCPauseMillis=20 -XX:InitiatingHeapOccupancyPercent=35 -XX:+ExplicitGCInvokesConcurrent -Djava.awt.headless=true"

## JVM JMX options
JVM_JMX_OPTS="-Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.authenticate=false  -Dcom.sun.management.jmxremote.ssl=false "

#JVM Options
JVM_OPTS="${JVM_HEAP_OPTS} ${JVM_PERFORMANCE_OPTS} ${JVM_JMX_OPTS}"

#Application Options
APP_OPTS="-jar ${LIB_FILE_PATH} --spring.config.location=${CONFIG_FILE_PATH}"

echo ------------------------------------------------
echo - APPLICATION: ${APP_NAME}
echo - VERSION: ${VERSION}
echo - APP PATH: ${APP_PATH}
echo - LIB FILE PATH: ${LIB_FILE_PATH}
echo - CONFIG FILE PATH: ${CONFIG_FILE_PATH}
echo - JVM_HEAP_OPTS: ${JVM_HEAP_OPTS}
echo - JVM_PERFORMANCE_OPTS: ${JVM_PERFORMANCE_OPTS}
echo - JVM_JMX_OPTS: ${JVM_JMX_OPTS}
echo - JVM_OPTS: ${JVM_OPTS}
echo - APP_OPTS: ${APP_OPTS}
echo ------------------------------------------------
