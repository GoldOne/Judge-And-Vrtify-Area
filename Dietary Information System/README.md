Dietary Information System
==============================
You  are  required  to  write  a  Java  Application  that  uses  an 
interactive Graphical User Interface (GUI) based on the JFrame class using SWING 
GUI components. You will also be designing and implementing the software solution 
using  appropriate  data  structures  and  application  of  classes,  inheritance, 
polymorphism, and exception handling focusing more on use and evaluation of data 
structures and algorithms.
Your task is to develop a Java Application that lets the user to: 
1.  Load data from file,  
2.  Search for a food and beverage item,  
3.  Sort items in the order of the types of food and beverage,  
4.  Delete a menu item,  
5.  Display Items,  
6.  Save modified items to the file, and  
7.  Quit the application.  
This  will  become  part  of  a  Dietary  Information  System.  The  application  should 
contain a GUI as shown below. The GUI components should consist of the following 
panels. 
1.  A top panel that contains two Text fields, two ComboBoxes (drop down list) and five 
Labels. 
2.  A middle panel that contains a Text area to display the menu items.  
3.  A bottom panel that contains seven buttons which are  “Load Data”, “Search Items”, 
“Sort” Items, “Delete Menu Item”, and “Display Items”, “Save Menu items” and 
“Quit” 
The functions of the seven buttons are described below. 
1.   Load Menu Item 
Initially this button will be disabled. The user has to enter the correct user name and 
password for this button to be enabled. Clicking on the “Load Data” button should 
read the data file (this data file will be given on the course website) and load data into 
a LinkedList or ArrayList in the program and the loaded data should be displayed in 
the TextArea as shown in Figure 2. The “Load Data button should also populate the 
ComboBoxes with the names of Food and Beverage Items. The nutrition values to be 
displayed are Calorie, Saturated Fat, Cholesterol, Sodium, and Fiber as given in the 
data file. 
2  Search Items Button 
If a user wants to know the nutrition values of each one of the Food and Beverage 
items, then the user choses the items using the ComboBoxes and clicks the “Search” 
button. The TextArea displays the nutrition information of those two items as shown 
in Figure 3. 3.  Sort Items Button 
The  user  can  use  this  Command  Button  to  get  the  Menu  items  displayed  in  the 
TextArea  sorted  by  meu  type  of  Food  and  Beverage.  Select  an  appropriate  sort 
algorithm suitable for your application. 
4.  Delete Menu Item Button 
This button can be used to delete a chosen Food and or Beverage Item from the list. 
After clicking the ‘Delete Menu Item’ button the user chooses either a Food Item or a 
Beverage Item or both Food and Beverage Items using the ComboBoxes. The items 
getting  deleted  should  be  displayed  in  the  TextArea.  These  items  will  be  removed 
from the LinkedList/ArrayList.
5.  Display Items Button 
    This button enables the user to display items in the TextArea after deleting item/s. 
6.  Save Menu Items Button 
The user can save the list of Items after deletion to the file using this button  
7.  Quit Button 
The “Quit” menu should allow the user to exit from the application.  
Data Structures and Algorithms. 
You can use the classes given below as a guideline for your design. 
1.  MenuItem Class 
           You can use the MenuItem class from Assignment One with some modifications. 
a)  This class should have the fields to store: 
Menu name and the value of each of the five nutrition information  included. 
Additionally this class should also store the Item Type indicating it is a Food 
item or Beverage item. 
b)  It should also include constructor, mutator, and accessor methods,  
c)  A toString() method for displaying item details. 
d)  The MenuItem class should implement the Comparable interface and override 
the compareTo() method so that this can be used in sorting. 
2.  GUI Components class 
a)  This class should have the GUI components listed above. 
b)  It  should  have  the  methods  to  set  up  the  GUI  components  and  the  event 
handling methods.   
c)  This also contains the main method.   
Try  to  use  methods  to  avoid  repetitive  code  and  use  anonymous  inner  class  for 
event handing to improve clarity and maintainability. 
