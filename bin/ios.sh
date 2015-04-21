#!/bin/sh
set -eux

cd $(dirname $0)

java -jar ios-server-0.6.5-jar-with-dependencies.jar -simulators
