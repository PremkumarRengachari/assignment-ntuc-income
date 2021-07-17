# assignment-ntuc-income

Prerequisites:

Please ensure following software packages whether installed in your machine

Java 1.8 or above Apache Maven 3.6.0 or above. 

Please Download chrome driver and geckodriver (firefox) and keep it into drivers directory in project - (https://chromedriver.chromium.org/downloads) https://github.com/mozilla/geckodriver/releases?ref=hackernoon.com



Please clone or download the assignment project from below github link  https://github.com/PremkumarRengachari/assignment-ntuc-income

Please extract the project and keep in local machine. 

Execution steps :

Step 1 : Open CommonUtil class and provide your inputs such as git hub user name or id, repo name and browser name. Note : all inputs are given by defualt.

Step 2 : open command prompt or terminal and point to the “assignment-ntuc-income” directory 

Step 3 : Enter “mvn clean install -DskipTests” command 

Step 4 : if we need to execute Both api test and web ui test, enter "mvn test" command

Step 5 : if we need to execute only api test , enter "mvn test -Dtest=GithubSteps#apiTest" command

Step 6 : if we need to execute only web UI test , enter "mvn test -Dtest=Hooks,GithubSteps#webUiTest" command

Step 7 : After executed susccessfully, we can find the html reports in the reports directory

