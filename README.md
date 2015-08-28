# Selenium WebDriver In Practice
## Pre-requisites

You'll need to install

* Java 8.
* Maven.
* Firefox.

Running
---
This is an example application with many tests. To run just the application:

	mvn jetty:run
	
When it is ready you'll see "Started Jetty Server" printed onto the console.  You can then view the sample HTML pages at <http://localhost:8080>.

To stop this web site, Ctrl+C, or:

	mvn jetty:stop
	
To run a single test (using the Maven Failsafe Plugin):

	mvn failsafe:integration-test failsafe:verify -Dit.test=HelloWebDriverIT

On just in another browser, other than Firefox:

	mvn ... -Dwebdriver.capabilities.browserName=chrome

To run remotely:

	mvn ... -Dwebdriver.remote.url=$WD_URL

