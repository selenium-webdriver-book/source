# Selenium WebDriver In Practice
## Set-up

	brew install phantomjs

Running
---
This is an example application with many tests. To run just the application:

	mvn jetty:run
	
Ctrl+C to close it. Or you can do this:

	mvn jetty:stop

To run a single test with Jetty running (using the Maven Failsafe Plugin):

	mvn failsafe:integration-test failsafe:verify -Dit.test=HelloWebDriverIT

Run run all the tests:

	mvn verify -Prun-its

On just one browser:

	mvn ... -Dwebdriver.capabilities.browserName=firefox

To run remotely:

	mvn ... -Dwebdriver.remote.url=$WD_URL

When not all tests will pass, you can try and get a clearer picture using the site report:

	mvn site
	open target/site/failsafe-report.html

