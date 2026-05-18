# UberCuts: A Mock Application

This repository contains an implemenation of a mock application that is run and interacted with completely in the terminal. This project uses test driven development, 4 pillars of Object Oriented Programming, and the SOLID principles. The main design pattern this code uses is the MVC pattern, with our AppController.java representing the Controller, the TerminalView.java representing the View, and the other classes falling udner the Model section. We also use the Strategy pattern to open our classes for extension for future improvements.

## Get started

### 1. Prerequisites
- Java 17
- IntelliJ IDE
- Gradle 9.0.0

### 2. How to clone and build
A simple way to clone the project is to copy the repository URL in GitHub, go into IntelliJ, and go to 
File -> New -> Project From Version Control... 
And paste the URL there and click Clone.

Alternatively, you can run this in your terminal:
```git clone [<repo-url>](https://github.com/usd-comp-305/project-04-dakota-juliet-jake.git)
cd project-04-dakota-juliet-jake
./gradlew build
```

### 3. Run the Application
Once you have cloned the repository and built the project, you can run the mock application. To run it, open the Simulation.java file in src/main/java/edu.sandiego.comp305/ and run this file.

If you would like to run the tests, test files can be found in src/test/java/edu.sandiego.comp305/. You can run any of these files that is associated with one of the classes by clicking on it and hitting run.


## Project Design
- MVC Pattern: AppController.java, TerminalView.java, Model classes
- Strategy Pattern: PaymentedMethod interface, View interface

In our MVC Pattern, the Model and View do not communicate, and the Controller stands as the "middle man" between the two:
```
 ╭┄┄┄┄┄┄┄╮      ┌──────────┐      ┌──────────┐
 ┆ Model ┆  ←→  │Controller│  ←→  │   View   │
 ┆       ┆      │          │      │          │
 ╰┄┄┄┄┄┄┄╯      └──────────┘      └──────────┘           
```


## Tools
  * **JUnit**: test runner
  * **Mockito**: test doubles (mocks, stubs)


## Known Limitations
- Mock application, no real multi-user support
- No database storing user information for multiple sessions
- All interaction is done in a terminal, which can be improved upon, and our View interface allows for extension for the future here
- No real payment processing, it just grabs fake information and doesn't verify if the information is correct apart from basic character checks
- Payment interaction is very loose and just checks that you paid the minimum, but doesn't cap you on how much you can spend
