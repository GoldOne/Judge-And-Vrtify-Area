import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import au.edu.uow.ClientGUI.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment 4. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class JavaQuizClient 
{
	private Student student=new Student();
	
	private JFrame JavaQuiz=new JFrame();
	
	private static int port=40213;//default port
	
	private static String hostname="localhost";//defalut hostname
		
	private Socket socket;

	public static void main (String[] args) 
	{
		if(args.length!=0)
			new JavaQuizClient(args[0]);
		else
			System.out.println("Usage: hostname (or hostname:portnumber)");  
	}
	
	public JavaQuizClient(String a)
	{	
		if(a.indexOf(":")>0)//different ways for setting the hostname and port
		{
			String temp[]=a.split(":");
			hostname=temp[0];
			port=Integer.parseInt(temp[1]);
		}	
		else 
			hostname=a;
		
		final Properties props=new Properties();
		
		try 
		{
			FileInputStream in=new FileInputStream("JavaClientQuizGUI.conf");//reading the config files
			props.load(in);		
			in.close();
		} 
		catch (FileNotFoundException e) 
		{	
			e.printStackTrace();
		} 
		catch (IOException ed) 
		{	
			ed.printStackTrace();
		}
		
		int width=Integer.parseInt(props.getProperty("winwidth"));
		
		int height=Integer.parseInt(props.getProperty("winheight"));
		
		String style=props.getProperty("style");
		
		try
		{
			UIManager.setLookAndFeel(style);
			
			SwingUtilities.updateComponentTreeUI(JavaQuiz);//setting the look and feel	
		}
		catch(Exception ex)
		{
	        	ex.printStackTrace();
	    	}
		
		JMenuBar menuBar=new JMenuBar();//build the menu
		JMenu menu=new JMenu("Connection");
		JMenu he=new JMenu("Help");
		final JMenuItem Con=new JMenuItem("Connect");
		final JMenuItem Dis=new JMenuItem("Disonnect");
		Dis.setEnabled(false);
		
		JMenuItem Set=new JMenuItem("Set Server");//add menu items into menu
		JMenuItem exit=new JMenuItem("Exit");
		JMenuItem abt=new JMenuItem("About");
		menuBar.add(menu);
		menuBar.add(he);
		menu.add(Con);
		menu.add(Dis);
		menu.addSeparator();
		menu.add(Set);
		menu.addSeparator();
		menu.add(exit);
		he.add(abt);//drawing the menu items
		
	    	final JLabel bar=new JLabel("Connect to the server first.");//Set origin status
	    	Border border=BorderFactory.createLineBorder(Color.BLACK);
	    	bar.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0,0,0,0)));
	    	
	    	abt.addActionListener(new ActionListener()//about listener
	    	{
	    		public void actionPerformed(ActionEvent e)
	    		{
	    			JOptionPane.showMessageDialog(null,"<html><h1>Java Quiz Client Ver 1.0</h1><br/><h2 align='center'>based on Java sockets</h2><br/><h3 align='center'>by Yi Jin</h3></html>");
	    		}
	    	});
	    		
	    	exit.addActionListener(new ActionListener()//exit listener
	    	{
		    	public void actionPerformed(ActionEvent event) 
		    	{
		    		try 
				{
					socket = new Socket (hostname,port);
					
					if(socket.isClosed()==false)
					{
						OutputStream outStream=socket.getOutputStream();
						PrintWriter out=new PrintWriter(outStream,true);
		    				out.println("BYE");
		    				socket.close();
	    				}
				} 
				catch (IOException ex) 
				{	
					ex.printStackTrace();
				}
				
		       		System.exit(JFrame.NORMAL);
		   	}

        	});
        	
		final JTextField Name=new JTextField(10);//create panel to hold user information
		final JPanel middle=new JPanel();
		JLabel name=new JLabel("Your Name:");
		   
		middle.add(name); 
		middle.add(Name);
	  
		final JPanel center=new JPanel(new GridBagLayout());//create center panel
		final JPanel bottom=new JPanel(new BorderLayout());
		bottom.add(middle,BorderLayout.NORTH);//put middle to the tool bar
		bottom.add(bar,BorderLayout.SOUTH);
		GridBagConstraints constraints=new GridBagConstraints();

		JLabel word=new JLabel("<html><pre><h1 style='color: #ffffff' align='center'>"
		+"Java Quiz Client</h1></pre><br/><h2 align='center'><i>Created by Yi Jin</i></h2><br/><h2 align='center'>for</h2><br/><h2 align='center'><b>CSCI213 Assignment 4</b></h2></html>");
		center.setBackground(new Color(0,150,150));
		center.add(word, constraints);//set location for the middle word
		constraints.gridx=1;
		constraints.gridy=1;
	   	JavaQuiz.add(center,BorderLayout.CENTER);//put center at the centre in the frame
		JavaQuiz.add(bottom,BorderLayout.SOUTH);
        	
		Name.addActionListener(new ActionListener()//textfield listener
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(Name.getText().trim().length()==0||Name.getText().toString().trim()=="")
					JOptionPane.showMessageDialog(null, "Please enter your name first!!!");
				else
				{
					JOptionPane.showMessageDialog(null, "Please connect to the server!!!");
				}
			}
		});
		
		Con.addActionListener(new ActionListener()//conncetion lister
	    	{
	    		public void actionPerformed(ActionEvent eve)
	    		{
	    			if(Name.getText().trim().length()==0||Name.getText().toString().trim()=="")
					JOptionPane.showMessageDialog(null,"Please enter your name first !!!");
					else
					{
						try
						{
							if(port!=0)
							{
								socket = new Socket (hostname,port);
								bar.setText("Connected to "+hostname+": "+port);
							}
						}
						catch (UnknownHostException e) 
						{
							e.printStackTrace();
						} catch (IOException e) 
						{
							e.printStackTrace();
						}
						
						student.setName(Name.getText());
						Con.setEnabled(false);
						Dis.setEnabled(true);
						JavaQuiz.remove(center);
						bottom.remove(middle);
						new QuizClientGUIFrame(JavaQuiz,student,socket,Con,Dis);	
					}		
	    		}
	    	});
	    	
	    	Dis.addActionListener(new ActionListener()//disconnect listener
	    	{
	    		public void actionPerformed(ActionEvent event)
			{
				try 
				{
					OutputStream outStream=socket.getOutputStream();
					PrintWriter out=new PrintWriter(outStream,true);
	    				out.println("BYE");
	    				socket.close();
				} 
				catch (IOException e) 
				{	
					e.printStackTrace();
				}
				
				Con.setEnabled(true);
				Dis.setEnabled(false);	
				bar.setText("Disconnected");
			}
            	});
            			
		Set.addActionListener(new ActionListener()//set server
		{
			public void actionPerformed(ActionEvent ae)
			{
				String Port=(String)JOptionPane.showInputDialog(null, "Server:port", "Set Server",JOptionPane.PLAIN_MESSAGE,null,null,"localhost:40213");
				if(Port!=null)
				{
					String PORT[]=Port.split(":");
					hostname=PORT[0];
					port=Integer.parseInt(PORT[1]);
				}
				else
				{
					hostname="localhost";
					port=40213;
				}
			}
		});
		
	    	JavaQuiz.setVisible(true);//set other attributes for the frame
		JavaQuiz.setTitle("Java Quiz");
		JavaQuiz.setSize(width,height);
		JavaQuiz.setJMenuBar(menuBar);
		JavaQuiz.setLocationRelativeTo(null);
		JavaQuiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JavaQuiz.addWindowListener(new WindowAdapter()//whenever user close the window, it will disconnect the server immediately 
		{
			public void windowClosing(WindowEvent e)
			{
				try 
				{
					socket = new Socket (hostname,port);
					
					if(socket.isClosed()==false)
					{
						OutputStream outStream=socket.getOutputStream();
						PrintWriter out=new PrintWriter(outStream,true);
		    				out.println("BYE");
		    				socket.close();
	    				}
				} 
				catch (IOException ex) 
				{	
					ex.printStackTrace();
				}
			}
		});
	}
}
