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
	
Slow Tests
---

To find slow tests:

    mvn clean install
    mvn surefire-report:failsafe-report-only
    open target/site/failsafe-report.html 
	
Local Selenium Grid With Vagrant
---

You can run a mini Selenium Grid if you like:

    cd vagrant
    vagrant up 
    
Check it is working at <http://192.168.10.2:4444>. Please refer chapter 11 of the book for how to set-up Windows with IE10.

You can run your tests as follows:

    mvn clean install -Dwebdriver.remote=true -Dwebdriver.remote.url=http://192.168.10.2:4444/wd/hub

Problems
---
Please let us know in if you have any issues in [the forums](https://forums.manning.com/forums/selenium-webdriver-in-practice). If you are on Windows, then please [read this](https://forums.manning.com/posts/list/36669.page).