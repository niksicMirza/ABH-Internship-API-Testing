# rest_assured_testing_bidba
In this project I have automated an API smoke test for https://bidba.herokuapp.com/.

# Techonologies 

I have used following technologies in this project:
- Java 1.8
- Maven
- Required dependencies
- InteliJ (framework)


# Run the Test

- Install Java 1.8
- Install InteliJ
- Install Maven
- Install Allure Framework
- Open InteliJ 
- Check if the Google Chrome version is same as the version of WebDriver
- Clone this repository using InteliJ console
- Open project
- Open pom.xml file
- Check if the dependencies are up to date
- Install all dependencies
- In "Select Run/Debug Configuration" select accountCreationAndCheckoutProcess
- Click Run or Debug
- Open cmd and run ```allure serve *location_of_allure-results*```

# Run the Test from Command Line

- Navigate to the project folder
- Enter ```mvn test -DsuiteXmlFile="smoke.xml"``` if you want to run smoke test

# Jenkinks
As you can see, there is a Jenkinsfile in the project. It contains Jenkins pipeline which runs this test, create report and send email containing reports. 
There is also a stage which runs a smoke and regression test (https://github.com/niksicMirza/bidba_smoke_and_regression.git).

## Installation
You can download Jenkins from the following link: https://www.jenkins.io/download/. I would recommend downloading .war file. When it is downloaded, open your command line and
enter ```java -jar *location_of_war_file*``` and hit enter. Jenkins will be opened in localhost. If you want to make Jenkins public so you can use Github webhooks, you can use ngrok.

## Ngrok
You can download Jenkins from the following link: https://ngrok.com/. You need to create a free account in order to get Authtoken. When ngrok is downloaded and installed, open 
ngrok.exe file and enter ```./ngrok authtoken *your_token*```. Then, enter command ```ngrok http 8080 -host-header="localhost:8080"``` if your jenkins is run on port 8080. If not, just change the port. You will get public URLs which you can use for Github webhooks.



