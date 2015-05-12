import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuChoicesNutritionGUI extends JFrame 
{
	private static int errors=0;
	final private static int width=800;
	final private static int height=600;
	final private static int max_length=20;
	public MenuChoicesNutritionGUI()
	{
		JPanel top=new JPanel(new BorderLayout());
		JPanel top1=new JPanel(new FlowLayout());
		JLabel name=new JLabel("Customer Details  Name");
		JLabel tableNumber=new JLabel("Table Number");
		final JTextField cname=new JTextField(15);
		final JTextField number=new JTextField(5);
		number.setText("1");
		top1.add(name);
		top1.add(cname);
		top1.add(tableNumber);
		top1.add(number);
		top.add(top1,BorderLayout.NORTH);
		JPanel top2=new JPanel(new FlowLayout());
		JLabel menu=new JLabel("Menu Choices  Food");
		final JComboBox<String> food=new JComboBox<String>(MenuItem.getFood());
		JLabel drink=new JLabel("Beverage");
		final JComboBox<String> beverage=new JComboBox<String>(MenuItem.getDrink());
		top2.add(menu);
		top2.add(food);
		top2.add(drink);
		top2.add(beverage);
		top.add(top2,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);

		JPanel middle=new JPanel();
		final JTextArea show=new JTextArea(width/40,height/10);
		show.setEditable(false);
		middle.add(new JScrollPane(show));
		middle.setBorder(BorderFactory.createTitledBorder("Your Menu choices and Nutrition Information"));
		add(middle,BorderLayout.CENTER);

		JPanel buttom=new JPanel(new GridLayout(1,4));
		buttom.setBorder(BorderFactory.createTitledBorder("Command Buttons"));
		JButton enter=new JButton("Enter Data");
		JButton display=new JButton("Display Choices");
		JButton clear=new JButton("Clear Display");
		JButton exit=new JButton("Quit");
		buttom.add(enter);
		buttom.add(display);
		buttom.add(clear);
		buttom.add(exit);
		add(buttom,BorderLayout.SOUTH);
		
		final OrderedCustomer oc=new OrderedCustomer("",0);

		enter.addActionListener(new ActionListener()//enter listener
		{
			public void actionPerformed(ActionEvent event) 
		{
			if(errors==3)
			{
				JOptionPane.showMessageDialog(null,"You have made errors on table number for 3 times!!!","Game Over !!!",JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
			}
			if(cname.getText().trim().length()==0||cname.getText().toString().trim()=="")
			{
				JOptionPane.showMessageDialog(null,"Enter your name first!!!","Warning !!!",JOptionPane.WARNING_MESSAGE);	
				return;
			}
			else if(cname.getText().trim().length()>max_length)
			{
				JOptionPane.showMessageDialog(null,"Your name is too long!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(number.getText().trim().length()==0||number.getText().toString().trim()=="")
			{
				JOptionPane.showMessageDialog(null,"Table  number???","???",JOptionPane.QUESTION_MESSAGE);
				return;
			}
			else
			{
				String temp=number.getText().trim().toString();
				try
				{
					Integer.parseInt(temp);
				}catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null,"Please give me a number!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					errors++;
					return;
				}
				if(Integer.parseInt(number.getText().toString().trim())>8||Integer.parseInt(number.getText().toString().trim())<1)
				{
					JOptionPane.showMessageDialog(null,"There are only 8 tables!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					errors++;
					return;
				}
			}

			oc.setName(cname.getText().toString());
			oc.setNumber(Integer.parseInt(number.getText()));
			oc.Count(food.getSelectedIndex(),beverage.getSelectedIndex());
		}
		});

		display.addActionListener(new ActionListener()//display listener
		{
			public void actionPerformed(ActionEvent event) 
		{
			if(cname.getText().trim().length()==0||cname.getText().toString().trim()=="")
			{
				JOptionPane.showMessageDialog(null,"Enter your name first!!!","Warning !!!",JOptionPane.WARNING_MESSAGE);	
				return;
			}

			if(oc.check())
			{
				JOptionPane.showMessageDialog(null,"Please press enter data!!!"," Tips!!!",JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			show.append(oc.toString());
			show.append(MenuItem.displayFood(food.getSelectedIndex())+"\n");
			show.append(MenuItem.displayDrink(beverage.getSelectedIndex())+"\n\n");
			show.append("\t"+oc.show()+"\n");
			oc.clear();
		}
		});

		clear.addActionListener(new ActionListener()//clear listener
		{
			public void actionPerformed(ActionEvent event) 
		{
			show.setText("");
		}
		});

		exit.addActionListener(new ActionListener()//exit listener
		{
			public void actionPerformed(ActionEvent event) 
		{
			System.exit(JFrame.NORMAL);
		}
		});
	}

	public static void main(String[] args) 
	{
		MenuChoicesNutritionGUI ass1=new MenuChoicesNutritionGUI();
		ass1.setVisible(true);
		ass1.setTitle("COIT20256 Assignment1");
		ass1.setSize(width,height);
		ass1.setLocationRelativeTo(null);
		ass1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

