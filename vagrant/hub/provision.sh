#!/usr/bin/env bash
set -eux

apt-get update
apt-get install -y openjdk-7-jre
wget -c http://selenium-release.storage.googleapis.com/2.48/selenium-server-standalone-2.48.2.jar

cat > /etc/init/selenium-hub.conf <<EOF
# selenium-hub - Run A Selenium Hub.

description "Run A Selenium Hub"
start on runlevel [2345]
stop on runlevel [016]
respawn

exec java -jar /home/vagrant/selenium-server-standalone-2.48.2.jar -role hub
EOF

initctl reload-configuration
service selenium-hub start
