Java Quiz System
=====================
**Task 1: Implementation of the `Student` Class**


**Requirements:**


You should implement the `Student` class to support the Java quiz application to register the student’s name, record scores and give the final score at the end of the quiz. The API (public methods) of the `Student` class is defined as follows:

```Java
     public void setName(String name) 
     public String getName() 
     public void recordScore(boolean isCorrect) 
     public int getScore()
```

You should write code to implement above methods.This class could be in the package `au.edu.uow.UserInterface`.


Suggestions: Before you implement most functionality of your classes, the Java quiz application provided in the Appendix A may not be able to run properly. You can make a small application to test your classes while you develop them. It is a good idea that you always have a working program as you progress in implementing more methods.


**Task 2: Design and Implementation of the `UserInterface` Class**


**Requirements:**


You should design and implement a `UserInterface` class to support the Java quiz application to provide a console based user interface to display information and take user input. A sample screen shot that shows how this class interacts with users is shown in the Appendix C. This class should have at least the following methods as required by the Java quiz application:


     public Student getStudent()
     public void startQuiz(List quiz, Student student) 
     public void showStudentMarks(Student student)


The `getStudent()` method registers the student in a simple login process; the `startQuiz()` method presents the questions and records answers; the `showStudentMarks()` method shows the student marks. This class should be in the package `au.edu.uow.UserInterface`.


**Task 3: Design and Implementation of the `QuestionLibrary` Class**


**Requirements:**


You should design and implement a `QuestionLibrary` class to support the Java quiz application to store all questions of the question library and make quizzes from the question library. This class should have at least the following methods as required by the Java quiz application:


   public static boolean buildLibrary(String qFile)
   public static List makeQuiz(int noOfQuestions)


The `buildLibrary()` method loads all questions from a question library file with a format as specified in the Appendix D to build a list of all questions; the `makeQuiz()` method makes a quiz with the specified number of questions from the question library. Each quiz should not have duplicates of questions and quizzes in different runs should contain different sets of randomly selected questions. This class should be in the package `au.edu.uow.QuestionLibrary`.


**Task 4: Design and Implementation of Classes to Represent Questions**


**Requirements:**


You should design and implement two classes called `MultipleChoiceQuestion` and `TrueAndFalseQuestion` that implement the `Question` interface to represent the two types of questions. You may create more supporting classes if appropriate. You should not convert the true and false questions to a special case of multiple-choice questions. These classes should be in the package `au.edu.uow.QuestionLibrary`.


**Task 5: Creation of the API Documentation**


**Requirements:**
You should use the `javadoc` tool to produce the API documentation of your classes. You should have made necessary documentation comments while you code your program. All the documents should go into a subdirectory called `doc` in the directory where the Java quiz application is. You should produce documentation for both public and private methods and include your name as the author. The Java API documentation would be a good reference for you to determine what and how much information should be documented for your classes, methods and class fields.


You can produce the documentation by issuing the following command in the directory where the Java quiz application is:

    javadoc -private -tag author:a:"Author:" -d doc au.edu.uow.UserInterface au.edu.uow.QuestionLibrary
