package au.edu.uow.QuestionLibrary;

import java.util.*;
import java.io.*;
/**
 * This is the class that get questions from file and random 5 to quiz.<br>
 * @author Yi Jin, 4370673, yj742
 *
 */
public class QuestionLibrary
{
	/**
	 * This constant specifies declared questions list.
	 **/
	private static List<Question> quiz=new ArrayList<Question>();
	/**
	 * This is reading all questions from file.
	 * @see #makeQuiz(int)
	 * @param qfile The name of file typed by user
	 **/
	public static boolean buildLibrary(String qFile)
	{
		File file = new File(qFile); // Create file

		try 
		{
			// Create a buffered reader to read each line from a file.
			BufferedReader in = new BufferedReader(new FileReader(file));
			String s;
			// Read each line from the file and echo it to the screen.
			while ((s = in.readLine()) != null) 
			{
				
				switch(s)
				{
					case "<MQuestion>":
							  List<String> question =new ArrayList<String>();
							  List<String> choices =new ArrayList<String>();
							  int answer=0;
							  while(!s.equals("</MQuestion>"))
							  {
							  	s=in.readLine();
							  	switch(s)
							  	{
								  	case"<question>":
									  		s=in.readLine();
									  		
									  		while(!s.equals("</question>"))
									  		{	
									  			question.add(s);
									  			//System.out.println(s);
									  			s=in.readLine();
											}
											break;
									case  "<answer>":
											s=in.readLine();
											while(!s.equals("</answer>"))
											{	
												
												answer=Integer.parseInt(s);
												//System.out.println(s);
									  			s=in.readLine();
										
											}
											break;
									
									case "<choices>":
											s=in.readLine();
											while(!s.equals("</choices>"))
											{	
												
												choices.add(s);
												//System.out.println(s);
									  			s=in.readLine();
										
											}
											break;
								}	
							  }
							  MultipleChoiceQuestion mq = new MultipleChoiceQuestion(question,answer,choices);
							  quiz.add(mq);
							  break;
					case "<TFQuestion>":
							  List<String> _question =new ArrayList<String>();
							  String _answer=null;
							  while(!s.equals("</TFQuestion>"))
							  {
							  	s=in.readLine();
							  	switch(s)
							  	{
								  	case"<question>":
									  		s=in.readLine();
									  		while(!s.equals("</question>"))
									  		{	
									  			_question.add(s);
									  			//System.out.println(s);
									  			s=in.readLine();
									
											}
											break;
									case  "<answer>":
											s=in.readLine();
											while(!s.equals("</answer>"))
											{	
												_answer=s;
												//System.out.println(s);
									  			s=in.readLine();
										
											}
											break;
								}
								
							  }
							  TrueAndFalseQuestion tfq = new TrueAndFalseQuestion(_question,_answer);
							  quiz.add(tfq);
							  break;
				}
				
			}
			// Close the buffered reader, which also closes the file reader.
			in.close();
		} 
		catch (FileNotFoundException e1) 
		{
			// If this file does not exist
			System.err.println("File not found: " + file);
			return false;
		} 
		catch (IOException e2)
		{
			// Catch any other IO exceptions.
			System.err.println(e2);
			return false;
		}
		return true;
	}
	/**
	 * This is make a random of 5 questions for quiz
	 * @see #buildLibrary(String)
	 * @param noOfQuestions The number of questions in the quiz
	 **/
	public static List<Question> makeQuiz(int noOfQuestions)
	{
		Collections.shuffle(quiz);
		return quiz;
	}
	
}