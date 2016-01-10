JavaDerby
=====================

###Structure of the Java packages:
The Java classes created in this assignment will have the following package structure. You may add more supporting classes in appropriate packages if necessary.


    /WorkingDirectory
                  JavaQuizDB.java
                  derby.jar
                  /au/edu/uow
                            /UserInterface
                                    UserInterface.class
                                    Student.class
                            /QuestionLibrary
                                    Question.class
                                    MultipleChoiceQuestion.class
                                    TrueAndFalseQuestion.class
                            /QuestionDB
                                    QuestionDB.java
                                    MyQuestionDB.java


The `UserInterface` and `QuestionLibrary` packages with documentation are provided.

**Task 1: Database management**


**Requirements:**


You program will be responsible to setup the database, create and populate the table, and remove the table when it is no longer needed. The Derby RDMS will be used in embedded mode. The release of the Derby database engine and embedded driver library, `derby.jar`, to be used in this assignment is provided. This library should be located in your working directory.


You should use a properties file, called `database.properties`, located in your working directory to manage the database. The file contains the following properties:


    jdbc.drivers
    jdbc.url
    derby.system.home


The Derby system home directory should be called `MyQuestionDB` located in your working directory, which is set using `System.setProperty` method before the JDBC driver is loaded. The database to store all question should be called `JavaQuestionDB` in the Derby system home directory.


**Task 2: Database creation and population**


**Requirements:**


All questions are stored in a table called Questions in the database. The table is created using the following SQL statement:

```SQL
    CREATE TABLE Questions (Q_ID INT NOT NULL GENERATED ALWAYS AS
                                                 IDENTITY (START WITH 1, INCREMENT BY 1),
                                                 question CLOB,
                                                 choices CLOB,
                                                 answer INT,
                                                 CONSTRAINT primary_key PRIMARY KEY (Q_ID))
```
 
You should populate the table using the `addQuestion` method specified in the `QuestionDB` interface with questions from the question file with a format as specified in the Appendix C. This table will store both multiple choice questions and true-and-false questions.


Before the program exits, the table should be removed. Each execution of the program will start with a new table.


All above functions should be implemented programmatically in your program.


**Task 3: Design and Implementation of the `MyQuestionDB` Class**


**Requirements:**


You should design and implement a class called `MyQuestionDB` to implement the `QuestionDB` interface to interact with the database. This class should be created in the `au.edu.uow.QuestionDB` package. Read the `QuesitonDB` interface to understand the requirements for the implementation.


This class should use JDBC API to interact with a Derby database in the embedded mode.


The `buildQuestionDB` method creates and populates the database table with questions from the question file using the `addQuestion` method specified in the `QuestionDB` interface. The `makeQuiz` method should return a list of randomly selected questions from the database using the `getQuestion` method specified in the `QuestionDB` interface. You should use the classes provided in the `QuestionLibrary` package to represent and manage both types of the questions. Read the accompanying documentation of the package to understand the usage of the package.


The JDBC driver and database URL should be loaded into `Properties` object from the `database.properties` file.


Each method specified in the `QuestionDB` interface must be implemented by interacting directly with the database. The program should not buffer a collection of question objects.
