package au.edu.uow.UserInterfaceGUI;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import au.edu.uow.QuestionLibrary.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class QuizGUIFrame 
{		
	private JFrame Main;//the only frame
	private Student std;
	private int current=0;//current question
	private String filename;
	private Properties props;
	private int numofquestion=0;
	private String Choices="";
	private String Questions="";
	private ButtonGroup group=new ButtonGroup();
	
	public QuizGUIFrame(JFrame main, Student student, Properties prop)
	{
		Main=main;//initialization
		std=student;
		props=prop;
		
		filename=props.getProperty("file");//get parameters from config file
		numofquestion=Integer.parseInt(props.getProperty("questions"));
		
		JPanel base=new JPanel(new GridLayout(2,0));//create new panel
		JPanel question=new JPanel();//question part for display
		question.setBackground(Color.white);
		JPanel answer=new JPanel(new BorderLayout());//choices and next buuton part for display
		final JPanel choices=new JPanel(new GridBagLayout());//hold choices space
		JPanel button=new JPanel();//hold button space
		
		boolean isQuestionLibraryReady=QuestionLibrary.buildLibrary(filename);
		
		if(isQuestionLibraryReady==false)//reading and make questions
			System.out.println("All Qustions are not well prepared !!!");
		
		final List<Question> quiz=QuestionLibrary.makeQuiz(numofquestion);
		
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
		
		answer.add(choices, BorderLayout.NORTH);
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
					JPanel bye=new JPanel(new GridBagLayout());
					GridBagConstraints con=new GridBagConstraints();
					JLabel score=new JLabel("<html><h2><b>Result of "+std.getName()+": "+std.getScore()+" out of "+numofquestion+"</b></h3></html>");
					JButton exit=new JButton("<html><h5>Exit</h5></html>");
					
					exit.addActionListener(new ActionListener() 
					{
			           		public void actionPerformed(ActionEvent event) 
			           		{
			                		System.exit(JFrame.NORMAL);
			            		}
			        	});
			        	
					result.add(score,con);//set position
					con.gridx=1;
					con.gridy=1;
					bye.add(exit,con);
					
					Final.add(result,BorderLayout.CENTER);//put them into final label
					Final.add(bye,BorderLayout.SOUTH);
					
					Main.add(Final,BorderLayout.CENTER);//put final to main frame
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
