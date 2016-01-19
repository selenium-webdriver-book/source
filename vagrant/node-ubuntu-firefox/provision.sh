#!/usr/bin/env bash
set -eux

apt-get update
apt-get install -y firefox openjdk-7-jre xvfb
wget -c http://selenium-release.storage.googleapis.com/2.48/selenium-server-standalone-2.48.2.jar

cat > /etc/init/selenium-node.conf <<EOF
# selenium-node - Run A Selenium Node.

description "Run A Selenium Node"
start on runlevel [2345]
stop on runlevel [016]
respawn
exec xvfb-run java -jar /home/vagrant/selenium-server-standalone-2.48.2.jar -role node -hub http://192.168.10.2:4444/grid/register
EOF

initctl reload-configuration
service selenium-node restart
