# Maven Project Instructions

This project demonstrates how to compile a Maven project, package it into a JAR file, and run it from the command line.

## Prerequisites

1. **Java JDK**: Ensure you have JDK 22 installed.
   - [Download JDK](https://www.oracle.com/java/technologies/javase-jdk22-downloads.html)
   - Set the `JAVA_HOME` environment variable to your JDK installation directory.
   - Add `JAVA_HOME/bin` to your system `PATH`.

2. **Apache Maven**: Ensure you have Maven 4.0.0 installed.
   - [Download Maven](https://maven.apache.org/download.cgi)
   - Add Maven's `bin` directory to your system `PATH`.

## Project Structure
<pre>
StepanovIvanDmitrievichKI23-171b_8pr_1varian  
│
├── src
│    ├── main
│    │     └── java
│    │           └── org
│    │                └── example
│    │                       ├── ConfigurationManager.java
│    │                       ├── DiningTable.java
│    │                       ├── Fork.java
│    │                       ├── Main.java
│    │                       └── Philosopher.java
│    └── resources
├── target
├── .gitignore
└── pom.xml
</pre>

## Building the Project

1. Open a terminal and navigate to the root directory of the project.

2. Clean and package the project using Maven:
    ```sh
    mvn clean package
    ```

3. After the build completes, you should see a JAR file in the `target` directory:
    ```
    target/StepanovIvanDmitrievichKI23-171b_8pr_1varian-1.0-SNAPSHOT.jar
    ```

## Running the JAR File

1. To run the JAR file, use the following command in the terminal:
    ```sh
    java --enable-preview -jar target/StepanovIvanDmitrievichKI23-171b_8pr_1varian-1.0-SNAPSHOT.jar
    ```

