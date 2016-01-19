#!/usr/bin/env bash
set -eux

wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | sudo apt-key add -
sudo sh -c 'echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google.list'

apt-get update
apt-get install -y google-chrome-stable openjdk-7-jre xvfb unzip
wget -c http://selenium-release.storage.googleapis.com/2.48/selenium-server-standalone-2.48.2.jar
wget -c chromedriver.storage.googleapis.com/2.20/chromedriver_linux64.zip

if [ ! -e chromedriver ]; then
  unzip chromedriver_linux64.zip
fi

chmod +x chromedriver

cat > /etc/init/selenium-node.conf <<EOF
# selenium-node - Run A Selenium Node.

description "Run A Selenium Node"
start on runlevel [2345]
stop on runlevel [016]
respawn
exec xvfb-run java -Dwebdriver.chrome.driver=/home/vagrant/chromedriver -jar /home/vagrant/selenium-server-standalone-2.48.2.jar -role node -hub http://192.168.10.2:4444/grid/register
EOF

initctl reload-configuration
service selenium-node restart
