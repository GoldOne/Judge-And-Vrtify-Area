Menu Choices Nutrition Java GUI
================================

Your task is to develop a **Java Application** that lets the user to choose food and beverage 
item for a lunch menu and displays the nutrition values for the individual items and total for 
the lunch menu. The application should contain a GUI as shown below. The GUI components 
should consist of the following panels. 


  1.  A top panel that contains two Text fields, two ComboBoxes (drop down list) and six 
Labels. 
  2.  A middle panel that contains a Text area to display the menu choices and nutrition 
values.  
  3.  A bottom panel that contains four buttons which are “Enter Data” button, “Display 
Choices” button, “Clear Display” button and “Quit” button.  


The functions of four buttons are described below. 
**  1. Enter Data **
        The user enters the name and table number through the text fields on the GUI, and 
selects food and beverage for the lunch menu. The list of food and beverage items to 
be  used  is  provided  in  Tables  1-2  given  below.  These  Tables  contain  the 
corresponding nutrition values also. 

**Table 1. Beverage Items and Nutrition Values**
|  Item |  Calories (Kcal) | Saturated Fat(g) | Cholesterol (mg) | Sodium (mg) | Fiber (g) |
|:--------------:|:----------------:||:------------------:|:------------------:||:--------------:|:------------:|
|Caffe Latte|140| 4|20|105| 0| 
|Orange Juice|220|0|0|5|1 |
|Root Beer| 300|0 |0| 45| 0| 
|Sierra Mist| 300|0|0|60| 0| 
|Sweet Tea|250| 0|0|20| 0| 
|Vanilla Latte| 240|4 | 20| 105 |0| 

![Initial Screen Display](http://i11.tietuku.com/d472ee5a31f8231a.png)
<p align='center'><b>Initial Screen Display</b></p>

Table 2. Food items and Nutrition Values 
Item  Calories 
(Kcal) 
Saturated 
Fat(g) 
Cholesterol 
(mg) 
Sodium (mg)  Fiber (g) 
Caprese 
Sandwich, 
550  13  30  860  3 
Chicken 
Pomodoro 
Sandwich 
680  13  105  1410  4 
Egg on a 
Bagel  
510  6  350  880  2 
Macaroni & 
Cheese 
710  24  125  1560  3 
Meat Lasagna  470  11  100  1080  5 
Roasted 
Angus Steak  
630  4  80  1100  3 

The user should enter required data and click on “Enter data” button. Note that after “Enter 
Data”  button  is  clicked,  the  Name  Textfield  must  be  cleared  as  blank,  the  TableNumber 
Textfield should be set to its initial value of ‘1’and the two ComboBoxes must be set back to 
its default status. 
 
 
2. Display Choices 
Clicking on the “Display Choices” button should display the selected menu choices, 
including nutrition values for each of the nutrition information, and the total nutrition 
information for the whole menu. The nutrition values to be displayed are Calorie, 
Saturated Fat, Cholesterol, Sodium, and Fiber as shown below. The total of each 
nutrition value is calculated by adding the corresponding nutrition value for each 
item.  

3.  Clear Display  
Clicking on the “Clear Display” button should clear all contents from the display area 
and set the Textfields and ComboBoxes to their default values.  
 
4. Quit  
The “Quit” button should allow the user to exit from the application.  
 
Data Structures. 
You can use the classes given below as a guideline for your design. 
1.  Customer class 
a)  Include appropriate fields to store: 
 Customer's first name:  Maximum width 20 characters. 
Selected table number: It should be an integer value between 1and 8 with a 
default value  of 1. Raise an exception for wrong entry and allow the user to 
re-enter the value for 3 times. 
b)  A constructor and necessary accessor and mutator methods to get and set 
values 
c)  A toString method that displays the customer details 
2.  MenuItem Class 
a)  This class should have the fields to store: 
Menu name and the value of each of the five nutrition information included. 
b)  It should also include constructor, mutator, and accessor methods,  
c)  A toString method for displaying item details. 
3.  OrderedCustomer class 
a)  This  calss  is  extends  the  Customer  class. This  class  should  have  a  member 
field to store the list of menu items chosen by the customer by clicking the 
‘Enter Data’ button. 
This class should have: 
b)  Constructor, accessor and mutator methods. 
c)  A toString method for displaying the OrderedCustomer details. 
d)  A method to calculate the total of each of the nutrition values. 
 4.      GUI Components class 
a)  This class should have the GUI components listed above. 
b)  It should have the methods to set up the GUI components and the event 
handling methods.   
c)  This also contains main method.   
