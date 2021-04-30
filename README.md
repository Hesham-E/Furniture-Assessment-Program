# Lowest Price Application

This was a project that was completed for the Final Project for ENSF409. It is an Terminal Based application, where the user can make orders based on the the inventory listed on the inventory.sql file and calculate the lowest price of the desired order.

To run the program:
Ensure you have OpenJDK 11.0 or over Installed

Move directly local with edu using the Terminal or Command prompt

Use the following commands, based on your OS, to compile the program

Compile using Windows
```bash
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Main.java
```
Compile Using Linux/MacOS
```bash
javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Main.java
```
Run the program by typing the following commands

Run Using Windows
```bash
java -cp .;lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.Main
```
Run Using Linux/MacOS
```bash
java -cp .:lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.Main
```
Follow the instructions in the Terminal to make your order

We have included testing files and documentation to make the code easy to read and check if the program works

To run the tests, follow the following commands
Compile the program
Compile using Windows
```bash
javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Main.java
```
Compile Using Linux/MacOS
```bash
javac -cp .:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Main.java
```
Compile the tests

Compile the tests using the commands below

to compile (Windows):
```bash
javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/[TESTNAMEHERE].java
```
to compile (Linux/MacOS):
```bash
javac -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/[TESTNAMEHERE].java
```
Finally, run the tests

to run(Windows):
```bash
java -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.[TESTNAMEHERE]
```
to run(Linux/MacOS):
```
java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.[TESTNAMEHERE]
```
TESTNAMEHERE corresponds to the test name you wish to run, where they can be found in edu/ucalgary/ensf409
