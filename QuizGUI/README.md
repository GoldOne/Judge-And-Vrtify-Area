Quiz GUI
=====================
###Structure of the Java packages:


The Java classes created in this assignment will have the following package structure. You may add more supporting classes in appropriate packages if necessary.


    /WorkingDirectory
                  JavaQuizGUI.java
                  /au/edu/uow
                          /UserInterfaceGUI
                                    QuizGUIFrame.java
                                    (Other GUI related classes)
                                    Student.class
                          /QuestionLibrary
                                    Question.class
                                    MultipleChoiceQuestion.class
                                    TrueAndFalseQuestion.class
                                    QuestionLibrary.class
                                    
                                    
The `Student` class and `QuestionLibrary` package with documentation are provided


###Task 1: GUI Design and Implementation


The user requirements of your Java quiz GUI application are specified by the following program flow in 3 stages.


####Stage 1 – Registration Screen:


Fig. 1 is the screen of the GUI in first stage. Your program window should be located at the centre of the screen when it starts. It provides an interface to register the student name. In this screen, there should be one tool bar at the top containing a “**Score**” button, an “**Exit**” button and an area for student name registration. There should be sufficient space between buttons and the registration area. The registration area should contain a label to guide student to input the name in a text field, a text field to input the name and a “**Register**” button. All of them should locate closely to indicate they are in a group to serve one purpose.


A message is display at the centre (vertically and horizontally) of the program window as shown in Fig. 1. The message is displayed in three centre-aligned lines with the first line displaying the program name with a larger font size and in a different colour from the following two lines.


Before the name is typed into the name field, a dialogue box should pop up to remind the registration if the “**Score**” button or “**Register**” button is pressed, or a return key is pressed in the text field, as shown in the Fig 1. The dialogue box should have the same *Look and Feel* as the main frame.


After the student name is typed into the text field and a return key is pressed or the register button is pressed, the program should progress to the next stage.


At any stages, if the **Exit** button is pressed, the program should exit.


![Fig 1: First stage screen shot](http://i11.tietuku.com/31437b767d5f1aa0.png)
<h1 style="text-align:center">**Fig 1: First stage screen shot**</h1>


###Task 2: Program Configuration


Requirements:
Your program should be configurable. Users of your program should be able to configure the Look and Feel, the window size, the number of questions in each quiz session and the question file name using a configuration file.
A Java program can set up its own configurable attributes, called program attributes. Program attributes allow the user to configure various startup options, preferred window size, and so on for the program. Sometimes the term preferences is used instead of program attributes.
You should create a configuration file named “JavaQuizGUI.conf” for users to configure the program. Your program should use Java Properties class API to load the program attributes into your program. At least, the following configurations should be included in your configuration file.
 The size of your program window;
 The Look and Feel of your program;
 The number of questions in each quiz session;
 The Question file name;
The configuration file should accompany your program and in the same directory as your GUI application.

In your “JavaQuizGUI.conf” file, there should be a key/attribute pair as bellow to set the Look and Feel of your program to Java Metal Look and Feel:
LookAndFeel=javax.swing.plaf.metal.MetalLookAndFeel
