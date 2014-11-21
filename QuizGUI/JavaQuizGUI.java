import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import au.edu.uow.UserInterfaceGUI.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class JavaQuizGUI 
{
	private Student student=new Student();
	
	private JFrame JavaQuiz=new JFrame();

	public static void main (String[] args) 
	{
		new JavaQuizGUI();  
	}
	
	public JavaQuizGUI()
	{	
		final Properties props=new Properties();
		
		try 
		{
			FileInputStream in=new FileInputStream("JavaQuizGUI.conf");//reading the config files
			
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
		
		JToolBar toolBar=new JToolBar("Menu");//create the toolbar
	    	JButton score=new JButton("Score");
	    	JButton exit=new JButton("Exit");
	    	JLabel space=new JLabel("                                           ");
	    	toolBar.add(score);
	    	toolBar.add(exit);
	    	toolBar.add(space);//put all elements into toolbar
	    	
	    	exit.addActionListener(new ActionListener()//exit listener
	    	{
		    	public void actionPerformed(ActionEvent event) 
		    	{
		       		System.exit(JFrame.NORMAL);
		   	}

        	});
        	
		final JButton enroll=new JButton("Register");//create elements for input user name
		final JTextField Name=new JTextField(10);//create panel to hold user information
		final JPanel middle=new JPanel();
		JLabel name=new JLabel("Your name");
		   
		middle.add(name); 
		middle.add(Name);
		middle.add(enroll);//put all user information and button into middle panel
		toolBar.add(middle);//put middle to the tool bar
		JavaQuiz.add(toolBar,BorderLayout.NORTH);//put toolbar to the frame
	  
		final JPanel center=new JPanel(new GridBagLayout());//create center panel
		GridBagConstraints constraints=new GridBagConstraints();

		JLabel word=new JLabel("<html><pre><h1 style='color: #4f8ae8'>"
		+"Java Quiz</h1></pre> <br/><pre><i>Created by Yi Jin</i></pre></html>");

		center.add(word, constraints);//set location for the middle word
		constraints.gridx =1;
		constraints.gridy =1;
	   	JavaQuiz.add(center, BorderLayout.CENTER);//put center at the centre in the frame
	
		score.addActionListener(new ActionListener()//score button listener
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(Name.getText().trim().length()==0||Name.getText().toString().trim()=="")
					JOptionPane.showMessageDialog(null, "Register your name first");
				else
					JOptionPane.showMessageDialog(null, "Current score of " + student.getName() + " is " + student.getScore());
			}
		});
		
		enroll.addActionListener(new ActionListener()//register button listener
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(Name.getText().trim().length()==0||Name.getText().toString().trim()=="")
					JOptionPane.showMessageDialog(null, "Register your name first");
				else
				{
					student.setName(Name.getText());
					Name.setEnabled(false);
					enroll.setEnabled(false);
					JavaQuiz.remove(center);
					new QuizGUIFrame(JavaQuiz, student, props);
				}
			}
        	});
        	
		Name.addActionListener(new ActionListener()//textfield listener
		{
			public void actionPerformed(ActionEvent event) 
			{
				if(Name.getText().trim().length()==0||Name.getText().toString().trim()=="")
					JOptionPane.showMessageDialog(null, "Register your name first");
				else
				{
					student.setName(Name.getText());
					Name.setEnabled(false);
					enroll.setEnabled(false);
					JavaQuiz.remove(center);
					new QuizGUIFrame(JavaQuiz, student, props);
				}
		
			}
		});
		
	    	JavaQuiz.setVisible(true);//set other attributes for the frame
		JavaQuiz.setTitle("Java Quiz");
		JavaQuiz.setSize(width,height);
		JavaQuiz.setLocationRelativeTo(null);
		JavaQuiz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
