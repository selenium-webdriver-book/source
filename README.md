Selenium WebDriver In Practice
===
This is an example application with many tests. To run just the application:

	mvn jetty:run
	
Ctrl+C to close it. Or you can do this:

	mvn jetty:stop

Run run all the tests:

	mvn verify -Plocal

To run remotely:

	mvn verify -Dwebdriver.remote -Dwebdriver.remote.url=$WD_URL -Dwebdriver.baseUrl=http://selenium-webdriver-in-practice.github.io

When not all tests will pass, you can try and get a clearer picture using the site report:

	mvn site
	open target/site/failsafe-report.html
	
	
To start the local iPhone driver:

	./bin/ios.sh	
	
References
---
* <http://the-internet.herokuapp.com>