# OCTBatch2021
Clone this repository

Execute below cmd to run the test
mvn test -DXMLFiles=testng.xml

Note- Default it will run on chrome and it will use url as https://opensource-demo.orangehrmlive.com

If you want to use different browser and url
mvn clean test -DtestSuite=Regression.xml -Dbrowser=ChromeHeadless -Durl=https://opensource-demo.orangehrmlive.com

You can pass any parameter while running this build.
