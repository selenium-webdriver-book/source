set -eux

apt-get update
apt-get install -y firefox openjdk-7-jre
wget -c http://selenium-release.storage.googleapis.com/2.46/selenium-server-standalone-2.46.0.jar

cat > /etc/init/selenium-node.conf <<EOF
# selenium-node - Run A Selenium Node.

description "Run A Selenium Node"
start on runlevel [2345]
stop on runlevel [016]
respawn
exec java -jar /home/vagrant/selenium-server-standalone-2.46.0.jar -role node -hub http://192.168.50.4:4444/grid/register
EOF

initctl reload-configuration
service selenium-node start 