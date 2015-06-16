import java.io.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuItemsNutritionGUI extends JFrame
{
	final private static int WIDTH=1280;
	final private static int HEIGHT=768;
	private JPanel top;
	private JPanel top1;
	private JLabel name;
	private JLabel tableNumber;
	private JTextField cname;
	private JPasswordField number;
	private JPanel top2;
	private JLabel menu;
	private JComboBox<String> food;
	private JLabel drink;
	private JComboBox<String> beverage;
	private JPanel middle;
	private JTextArea show;
	private JPanel buttom;
	private JButton load;
	private JButton search;
	private JButton sort;
	private JButton delete;
	private JButton display;
	private JButton save;
	private JButton exit;
	private ArrayList<MenuItem> data;
	private String keep;//keep the first line of data file
	final private String title=" Item Type\tMenu Item Name\t\t\tCalorie\tSaturated Fat\t Cholesterol\tSodium\tFiber\n ---------------------------------------------------------------------------------------\n";

	public MenuItemsNutritionGUI()
	{
		top=new JPanel(new BorderLayout());//top GUI
		top1=new JPanel(new FlowLayout());
		name=new JLabel("User Name");
		tableNumber=new JLabel("Password");
		cname=new JTextField(15);
		number=new JPasswordField(10);
		top1.add(name);
		top1.add(cname);
		top1.add(tableNumber);
		top1.add(number);
		top.add(top1,BorderLayout.NORTH);
		top2=new JPanel(new FlowLayout());
		menu=new JLabel("Menu Choices  Food");
		final JComboBox<String> food=new JComboBox<String>();
		drink=new JLabel("Beverage");
		final JComboBox<String> beverage=new JComboBox<String>();
		top2.add(menu);
		top2.add(food);
		top2.add(drink);
		top2.add(beverage);
		top.add(top2,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);

		middle=new JPanel();//middle GUI
		show=new JTextArea(WIDTH/32,HEIGHT/8);
		show.setEditable(false);
		middle.add(new JScrollPane(show));
		middle.setBorder(BorderFactory.createTitledBorder("Menu Items and Nutrition Information"));
		add(middle,BorderLayout.CENTER);

		buttom=new JPanel(new GridLayout(1,4));//buttom GUI
		buttom.setBorder(BorderFactory.createTitledBorder("Command Buttons"));
		load=new JButton("Load Data");
		search=new JButton("Search Items");
		sort=new JButton("Sort Items");
		delete=new JButton("Delete Menu Item");
		display=new JButton("Display Items");
		save=new JButton("Save Menu Items");
		exit=new JButton("Quit");
		load.setEnabled(false);
		buttom.add(load);
		buttom.add(search);
		buttom.add(sort);
		buttom.add(delete);
		buttom.add(display);
		buttom.add(save);
		buttom.add(exit);
		add(buttom,BorderLayout.SOUTH);

		data=new ArrayList<MenuItem>();

		number.addKeyListener(new KeyAdapter()//check username and password
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(cname.getText().trim().length()==0||cname.getText().toString().trim()=="")
					{
						JOptionPane.showMessageDialog(null,"User Name should not be empty!!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
						return;
					}

					if(number.getPassword().equals("")||number.getPassword().toString().trim()=="")
					{
						JOptionPane.showMessageDialog(null,"Passowrd is empty!!!","Warning !!!",JOptionPane.WARNING_MESSAGE);
						return;
					}
					if(!cname.getText().equals("s0273749")||!Arrays.equals(number.getPassword(),new char[]{'q','w','e','r','t','y'}))
					{
						JOptionPane.showMessageDialog(null,"Incorrect User name or Password!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
						load.setEnabled(true);
				}
			}
		});

		load.addActionListener(new ActionListener()//load listener
		{
			public void actionPerformed(ActionEvent event)
			{
				show.setText("");
				data.clear();
				String type;
				String name;
				String temp="";
				show.append(title);

				try
				{
					Scanner fin=new Scanner(new FileReader("menuItemsData.txt"));//open file
					keep=fin.nextLine();

					while(fin.hasNextLine())
				   	{

						temp=fin.nextLine();
						StringTokenizer st=new StringTokenizer(temp,",");

						while(st.hasMoreTokens())
						{
							type=st.nextToken();
							name=st.nextToken();
							if(type.equals("food"))
								food.addItem(name);
							else
								beverage.addItem(name);
							if(name.length()<13&&!name.equals("Meat Lasagna"))
								name+="\t\t";
							if(name.length()>13&&name.length()<28)
								name+="\t";
							int values[]=new int[5];
							for(int i=0;i<5;i++)
								values[i]=Integer.parseInt(st.nextToken());
							data.add(new MenuItem(type,name,values));
						}

					}// end of while loop
					fin.close();//close file

			      	}catch(IOException ex)
			      	{
					JOptionPane.showMessageDialog(null,"Could not find menuItemsData.txt or file loading failed!!! ","Error !!!",JOptionPane.ERROR_MESSAGE);
					return;
			    	}

				temp="";
			      	Iterator it=data.iterator();
			      	while(it.hasNext())
			      		temp+=it.next().toString();
			      	show.append(temp);
			}
		});

		search.addActionListener(new ActionListener()//search listener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(data.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please loading the data first!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					return;
			    	}

			    	show.setText("");
				show.append(title);
				String temp="";

			    	boolean find=false;
			    	Date start=new Date();
			    	long begin=System.nanoTime();
			    	for(int i=0;i<data.size();i++)
			    		if(food.getSelectedItem().toString().trim().equals(data.get(i).getName().trim()))
			    		{
			    			find=true;
			    			temp+=data.get(i).toString();
			    		}

			    	for(int i=0;i<data.size();i++)
			    		if(beverage.getSelectedItem().toString().trim().equals(data.get(i).getName().trim()))
			    		{
			    			find=true;
			    			temp+=data.get(i).toString();
			    		}
			    	System.out.println("Search time: "+(System.nanoTime()-begin));
			    	if(find)
			    		show.append(temp);
			    	else
			    		show.append("Nothing found!!!\n");
			}
		});

		sort.addActionListener(new ActionListener()//sort listener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(data.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please loading the data first!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					return;
			    	}

				if(data.size()==1)
					return;

				MenuItem tmp;
				int j;
				long begin=System.nanoTime();
				for(int i=0;i<data.size()-1;i++)//insertion sort
				{
					tmp=data.get(i);
					for(j=i-1;j>=0&&data.get(j).compareTo(tmp)>0;j--)
						Collections.swap(data,j+1,j);
					MenuItem temp=data.get(j+1);
					temp=tmp;
				}
				System.out.println("Sort time: "+(System.nanoTime()-begin));

				show.setText("");
				show.append(title);
				String temp="";
				Iterator it=data.iterator();
			      	while(it.hasNext())
			      		temp+=it.next().toString();
			      	show.append(temp);
			}
		});

		delete.addActionListener(new ActionListener()//delete listener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(data.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please loading the data first!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					return;
			    	}

				String[] options=new String[] {(String)food.getSelectedItem(),(String)beverage.getSelectedItem(),"Both of them","Cancel"};
    				int response=JOptionPane.showOptionDialog(null, "Which to delete?","Question ???",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);

    				String temp="";
    				String tmp="";
    				boolean find=false;
			    	int p1=0,p2=0;//deleteitem index

			    	for(int i=0;i<data.size();i++)
			    		if(food.getSelectedItem().toString().trim().equals(data.get(i).getName().trim()))
			    		{
			    			find=true;
			    			temp+=data.get(i).toString();
			    			p1=i;
			    		}

			    	for(int i=0;i<data.size();i++)
			    		if(beverage.getSelectedItem().toString().trim().equals(data.get(i).getName().trim()))
			    		{
			    			find=true;
			    			tmp+=data.get(i).toString();
			    			p2=i;
			    		}
			    	if(find)
			    	{
	    				switch(response)
	    				{
	    					case 0:
	    						data.remove(p1);
	    						JOptionPane.showMessageDialog(null,(String)food.getSelectedItem()+" has been removed!!!"," Delete Sucess!!!",JOptionPane.INFORMATION_MESSAGE);
	    						food.removeItem(food.getSelectedItem());
	    						show.setText("");
							show.append(title);
							show.append(temp);
	    						break;
	    					case 1:
	    						data.remove(p2);
	    						JOptionPane.showMessageDialog(null,(String)beverage.getSelectedItem()+" has been removed!!!"," Delete Sucess!!!",JOptionPane.INFORMATION_MESSAGE);
	    						beverage.removeItem(beverage.getSelectedItem());
	    						show.setText("");
							show.append(title);
							show.append(tmp);
	    						break;
	    					case 2:
	    						data.remove(p1);
	    						data.remove(p2);
	    						JOptionPane.showMessageDialog(null,(String)food.getSelectedItem()+" and "+(String)beverage.getSelectedItem()+" have been removed!!!"," Delete Sucess!!!",JOptionPane.INFORMATION_MESSAGE);
	    						food.removeItem(food.getSelectedItem());
	    						beverage.removeItem(beverage.getSelectedItem());
	    						show.setText("");
							show.append(title);
							show.append(temp);
							show.append(tmp);
	    						break;
	    				}

	    				if(response==3)
	    				{
		    				show.setText("");
						show.append(title);
			    			show.append("Nothing to delete!!!\n");
					}
				}
				else
				{
					show.setText("");
					show.append(title);
			    		show.append("Nothing found to delete!!!\n");
			    	}
			}
		});

		display.addActionListener(new ActionListener()//display listener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(data.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please loading the data first!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					return;
			    	}

				show.setText("");
				show.append(title);
				String temp="";
				Iterator it=data.iterator();
			      	while(it.hasNext())
			      		temp+=it.next().toString();
			      	show.append(temp);
			}
			});

		save.addActionListener(new ActionListener()//save listener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(data.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Please loading the data first!!!","Error !!!",JOptionPane.ERROR_MESSAGE);
					return;
			    	}

				try
				{
					PrintWriter out=new PrintWriter(new FileWriter("menuItemsData.txt"));

					out.println(keep);
					String type;
					String name;

					for(int i=0;i<data.size();i++)
					{
						type=data.get(i).getType();
						name=data.get(i).getName();
						name=name.replaceAll("\t","");
						int values[]=new int[5];
						values=data.get(i).getValue();
						out.print(type+","+name+",");
						for(int j=0;j<5;j++)
							if(j<4)
								out.print(values[j]+",");
							else
								out.print(values[j]);
						out.println();
					}

					out.close();
					JOptionPane.showMessageDialog(null,"Data saved successfully!","",JOptionPane.INFORMATION_MESSAGE);

				}
				catch(Exception ex)
				{
					System.out.println("Save file fail!!!");
				}
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
		MenuItemsNutritionGUI ass2=new MenuItemsNutritionGUI();
		ass2.setFont(new Font("System",Font.PLAIN,14));
    	Font f=ass2.getFont();
	    FontMetrics fm=ass2.getFontMetrics(f);
	    int x=fm.stringWidth("Dietary Information System");
	    int y=fm.stringWidth(" ");
	    int z=ass2.getWidth()/2-(x/2);
	    int w=z/y;
	    String pad="";
	    pad=String.format("%"+w+"s",pad);
		ass2.setTitle(pad+"Dietary Information System");
    	ass2.setVisible(true);
		ass2.setSize(WIDTH,HEIGHT);
		ass2.setLocationRelativeTo(null);
		ass2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
