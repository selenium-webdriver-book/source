# Selenium WebDriver In Practice
## Pre-requisites

You'll need to install

* Java 8.
* Maven.
* Firefox.

Running
---
This is an example application with tests. To run the application:

	mvn jetty:run
	
When it is ready you'll see "Started Jetty Server" printed onto the console.  You can then view the sample HTML pages at <http://localhost:8080>.

To stop this web site, Ctrl+C, or:

	mvn jetty:stop
	
To run a single test (using the Maven Failsafe Plugin):

	mvn failsafe:integration-test failsafe:verify -Dit.test=HelloWebDriverIT

On just in another browser, other than Firefox:

	mvn ... -Dwebdriver.capabilities.browserName=chrome

To run remotely:

	mvn ... -Dwebdriver.remote=true -Dwebdriver.remote.url=$WD_URL

Problems
---
Please let us know in if you have any issues in [the forums](https://forums.manning.com/forums/selenium-webdriver-in-practice). If you are on Windows, then please [read this](https://forums.manning.com/posts/list/36669.page).