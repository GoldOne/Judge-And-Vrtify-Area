QuizCS
=====================
###Structures of the Client/server Packages


The Java classes created in this assignment will have the following package structure. You may add more supporting classes in appropriate packages if necessary.


+ **Server packages:**

```Java
    /WorkingDirectory/Server
                  JavaQuizServer.java
                  questions.xml
                  (Other configuration files if any)
                  /au/edu/uow
                          /Networking
                                  JavaClientHandler.java
                                  (Classes related to networking)
                          /UserInterface
                                  UserInterface.java
                                  (Other interface related classes)
                          /QuestionLibrary
                                  Question.java
                                  MultipleChoiceQuestion.java
                                  TrueAndFalseQuestion.java
                                  (Other related classes to load questions)
```

Your main server application will be created in `JavaQuizServer.java` file, which will load the Java questions from the questions file and serve concurrent clients. The server should send different quizzes to different clients. The client handling task class is implemented in `JavaClientHandler.java` file.

+ **Client packages:**

```Java
    /WorkingDirectory/Client
                  JavaQuizClient.java
                  (Other configuration files if any)
                  /au/edu/uow
                          /Networking
                               (Classes related to networking)
                          /ClientGUI
                                QuizClientGUIFrame.java
                                Student.java
                                (Other GUI related classes)
                          /QuestionLibrary
                                Question.class
                                MultipleChoiceQuestion.class
                                TrueAndFalseQuestion.class
```

Your main client application will be created in `JavaQuizClient.java` file, which will load your interface from `QuizClientFrame.java`. (Hint: develop your `QuestionLibrary` package in the server directory and copy only the compiled class files to the client directory.)


**Client/server Communication Protocol:**


The following methods are defined:


+ **REGISTER method:**

  -  Request: REGISTER *username*  
  

         The client sends the REGISTER message with a one word string *username* of the client user                    name to the server to register the user.
                
                
   - Response: OK
  
   
         Upon receiving the REGISTER message, the server sends back an acknowledgement message, OK, to                 the requesting client.
                
                
+ **GET QUESTION method:**
    - Request: GET QUESTION 
    

                    The client sends the GET QUESTION message to the server to request a Java quiz question.


    - Response: (*a question object*)
    
    
                    Upon receiving the GET QUESTION message, the server sends back a serialised question object to the requesting 
                    client.
                    Do not send all details of a question in strings but send only an object of a multiple choice question or 
                    true-and-false question class to the client using object serialisation.
                    
+ BYE method:

    - Request: BYE
    
    
                    The client sends the BYE message to the server to end a session.
                    
                    
    - Response: (*null*)
    
    
                    Upon receiving the BYE message, the server closes the requesting client connection.
                    The server does not need to send anything back to the requesting client.



**Server Requirements**


The server listens to a socket port for client connection requests. The server program can receive a command line argument for the listening port number. For example,


                                java JavaQuizServer 40213
                            
If the server program starts without any argument, it listens to a default port.


The server should display information about the listening port and status of connected users in the console. For example:


![Fig1](http://i11.tietuku.com/3bfd2389c71424cc.png)


**Client GUI Requirements**


The client program can receive a command line argument for the server name and/or port number. For example,

                                java JavaQuizClient localhost (or localhost:40213)
                            
            
If the client program starts without any argument, it connects to local host and a default port.


+ Before connection
        
        
After starting the client program, you should have the following interface. The status bar should show the current status of the program. Inapplicable items in a state should be disabled.

![Fig2](http://i11.tietuku.com/2d36d54de952d0f2.png)


The Connection menu has menu items shown in the figure.
  - The “Connect” item connects the client to the server. If the user attempts to connect, an information dialog should pop up as shown.

![Fig3](http://i11.tietuku.com/3b2c0a1908f91f02.png)


  - The “Disconnect” item disconnects the client from the server;
  - The “Set Server” item pops up an input dialog for the user to specify the server name
and port number as shown. The default server name should be “localhost:40213”.

![Fig4](http://i11.tietuku.com/d3072f0fc404b3fa.png)


  - The “Exit” item terminates the program. You need make sure to terminate the program
before properly disconnecting it from the server if it is connected.


The Help menu has one menu item called “About” that pops up an information dialog as shown:
![Fig5](http://i11.tietuku.com/b9aab6691ef9c622.png)
If the user closes the window at any time, you need make sure the client is disconnected from the server before the window closes. This can be done by setting the proper `DefaultCloseOperation` for the `JFrame` and processing the window event.


  + After connection
            
            
After the client has connected to the server and registered, you should have the following interface displaying question details and allow the user to select an answer as shown in the figure Question Screen.


The status bar should always show the status of the program.


When the user selects an answer, the next question will be presented. The client should request 5 questions from the server.


When the last question is presented, the `Next` button should be changed to “`Get Score`” button.


Once the user pressed the "`Get Score`”button, The connection should be terminated and the final score should be presented as shown in the figure Final Score Screen.


The user name and scores should be maintained by the `Student` object in the client program.
<p align='cneter'>![Fig6](http://i11.tietuku.com/7ef47b1bc0f769c6.png)</p>
<p align='center'><b>Figure: Question Screen</b></p>

![Fig7](http://i11.tietuku.com/80d67a7b3caae94d.png)
<p align='center'><b>Figure: Final Score Screen</b></p>
