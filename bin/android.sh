#!/bin/sh
set -eux

cd $(dirname $0)

java -jar selendroid-standalone-0.15.0-with-dependencies.jar
