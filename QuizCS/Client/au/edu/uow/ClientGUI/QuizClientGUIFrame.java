package au.edu.uow.ClientGUI;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import javax.swing.border.*;
import au.edu.uow.Networking.*;
import au.edu.uow.QuestionLibrary.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class QuizClientGUIFrame 
{		
	private JFrame Main;//the only frame
	private Student std;
	private int current=0;//current question
	private int numofquestion=5;
	private String Choices="";
	private String Questions="";
	private ButtonGroup group=new ButtonGroup();
	
	public QuizClientGUIFrame(JFrame main, Student student,final Socket socket,final JMenuItem Con,final JMenuItem Dis)
	{
		Main=main;//initialization
		std=student;
		
		JPanel base=new JPanel(new GridLayout(2,0));//create new panel
		JPanel question=new JPanel();//question part for display
		question.setBackground(Color.white);
		
		final List<Question> quiz;
		
		JavaServerHandler receive=new JavaServerHandler(student,socket);//recieve questions from server
		
		quiz=receive.getQuiz();
		
		JPanel answer=new JPanel(new BorderLayout());//choices and next buuton part for display
		final JPanel choices=new JPanel(new GridBagLayout());//hold choices space
		JPanel button=new JPanel();//hold button space
		
		final JLabel Question=new JLabel();//display question		
		for(int i=0;i<quiz.get(current).getQuestion().size();i++)//get first question
			Questions+=quiz.get(current).getQuestion().get(i) +"<br/>";
			
		final GridBagConstraints constraints=new GridBagConstraints();//set layout for question
		
		constraints.anchor=GridBagConstraints.FIRST_LINE_START;
		
		for(int i=0;i<quiz.get(current).getChoices().size();i++)//get choices and assign radio
		{	
			constraints.gridy=i;
			Choices=quiz.get(current).getChoices().get(i);
			JRadioButton radio=new JRadioButton(Choices);
			choices.add(radio,constraints);
			group.add(radio);
		}
		
		answer.add(choices,BorderLayout.NORTH);
		Question.setText("<html><h2><b>"+(current+1)+". "+Questions+"</b></h2></html>");
		question.add(Question);//put first question into question display part
	
		final JButton next=new JButton("<html><h5>Next</h5></html>");
		button.add(next);
		answer.add(button,BorderLayout.SOUTH);//put buuton for go to next question
		
		next.addActionListener(new ActionListener()//go to next question
		{
			public void actionPerformed(ActionEvent event) 
			{
				current++;
				
				if(current==numofquestion)//if finish quiz display score 
				{
					if(socket.isClosed()==false)
					{
						try 
						{
							OutputStream oS=socket.getOutputStream();
							PrintWriter out=new PrintWriter(oS, true);
			    				out.println("BYE");
							socket.close();
						} 
						catch (IOException e) 
						{	
							e.printStackTrace();
						}
					}
					
					Component[] select=choices.getComponents(); 
					
					for (int i=0;i<select.length;i++) 
					{ 
						JRadioButton check=(JRadioButton)select[i];
						if (check.isSelected())//check answer
							std.recordScore(quiz.get(current-1).compareAnswer(i+1));
					}
					
					Main.getContentPane().removeAll();
					JPanel Final=new JPanel(new BorderLayout());//set new panel for final score
					JPanel result=new JPanel(new GridBagLayout());
					result.setBackground(new Color(0,150,150));
					JPanel bye=new JPanel(new GridBagLayout());
					GridBagConstraints con=new GridBagConstraints();
					JLabel score=new JLabel("<html><h1 align='center' style='color: #ffffff'><b>Final Score<br/><br/><br/><br/></b></h1>"+"<h2 align='center'><b>"+std.getScore()+" out of "+numofquestion+"</b></h2></html>");
					Con.setEnabled(true);
					Dis.setEnabled(false);
				    	JLabel bar=new JLabel("Disconnected");//Set final status 
				    	Border border =BorderFactory.createLineBorder(Color.BLACK);
				    	bar.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(0,0,0,0)));//build status bar for the score display frame
			        	
					result.add(score,con);//set position
					con.gridx=1;
					con.gridy=1;
					
					Final.add(result,BorderLayout.CENTER);//put them into final label
					Final.add(bye,BorderLayout.SOUTH);
					
					Main.add(Final,BorderLayout.CENTER);//put final to main frame
					Main.add(bar,BorderLayout.SOUTH);
					Main.setVisible(true);
				}
				
				Questions="";//remove displayed question
				
				if(current<numofquestion)//display previous questions 
				{
					Component[] select=choices.getComponents(); 
					
					for (int i=0;i<select.length;i++) 
					{ 
						JRadioButton check=(JRadioButton)select[i];
						if(check.isSelected())//check answer
							std.recordScore(quiz.get(current-1).compareAnswer(i+1));		
					}
					
					if(current==numofquestion-1)//display next button
						next.setText("<html><h5>Get Marks</h5></html>");
					
					for(int i=0;i<quiz.get(current).getQuestion().size();i++)//get new question
						Questions+=quiz.get(current).getQuestion().get(i)+"<br/>";
					
					Question.setText("<html><h2><b>"+(current+1)+". "+Questions+"</h2></b></html>");//display new question
					
					Component[] com=choices.getComponents(); 
					 
					for (int i=0; i<com.length;i++)  
						choices.remove(com[i]);//remove previous questions' choices 
					
					choices.setVisible(false);
					
					for(int i=0;i<quiz.get(current).getChoices().size();i++)//get new choices and assign radio
					{
						constraints.gridy=i;
						Choices=quiz.get(current).getChoices().get(i);
						JRadioButton radio=new JRadioButton(Choices);
						choices.add(radio,constraints);
						group.add(radio);
					}
					
					choices.setVisible(true);
				}
			}
		});
		
		base.add(question);
		base.add(answer);//put the whole part of question(include choices) into base panel
		Main.add(base,BorderLayout.CENTER);//put base into main frame
		Main.setVisible(true);
	}
}
