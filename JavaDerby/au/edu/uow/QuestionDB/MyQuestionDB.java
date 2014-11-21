package au.edu.uow.QuestionDB;

import java.sql.*;
import java.io.*;
import java.util.*;
import au.edu.uow.QuestionLibrary.*;

/**
 * This class specifies the requirements of implementation QuestionDB interface 
 * 
 * This program is provided for the CSCI213 Assignment. <br>
 * Note: You should not modify this program but create a suitable
 *       class to implement this interface.
 *       
 * @author Yi Jin
 * @version v.1.0
 *
 */
public class MyQuestionDB implements QuestionDB
{
	private Connection conn;
	
	public boolean buildQuestionDB(String questionFile)
	{
		Statement st;
		
		try
		{
			Properties props = new Properties();
		
			FileInputStream in = new FileInputStream("database.properties");
			
			props.load(in);
		
			in.close();
		
			String drivers = props.getProperty("jdbc.drivers");
		
			if (drivers != null) 
				System.setProperty("jdbc.drivers", drivers);
				// setting system-wide properties
			
			String url = props.getProperty("jdbc.url");
			
			String homeDirectory = props.getProperty("derby.system.home");
			
			  /*
			  **  Load the Derby driver. 
			  **     When the embedded Driver is used this action start the Derby engine.
			  **  Catch an error and suggest a CLASSPATH problem
			   */
			try
			{
				Class.forName(drivers); 
			
				System.out.println(drivers + " loaded. ");
				
			}catch(java.lang.ClassNotFoundException e)    
			{
         			System.err.print("ClassNotFoundException: ");
          			System.err.println(e.getMessage());
         			System.out.println("\n    >>> Please check your CLASSPATH variable <<<\n");
     			}
			
			conn = DriverManager.getConnection(url);
			
			System.out.println("Connected to database... ");
			// Create (if needed) and connect to the database   
  			//   ## INITIAL SQL SECTION ## 
  			//   Create a statement to issue simple commands.  
			st = conn.createStatement();
			// Call utility method to check if table exists.
     			// Create the table if needed
   
			st.executeUpdate("CREATE TABLE Questions (Q_ID INT NOT NULL GENERATED ALWAYS AS"
			 		+" IDENTITY (START WITH 1, INCREMENT BY 1),"
			 		+" question CLOB,"
			 		+" choices CLOB,"
			 		+" answer INT,"
			 		+" CONSTRAINT primary_key PRIMARY KEY (Q_ID))");
			 		
			st.close();
			
			File file = new File(questionFile);
		
			List<Question> quiz=new ArrayList<Question>();
		
			try 
			{
				// Create a buffered reader to read each line from a file.
				BufferedReader fin = new BufferedReader(new FileReader(file));
				String s;
				// Read each line from the file and echo it to the screen.
				while ((s = fin.readLine()) != null) 
				{
				
					switch(s)
					{
						case "<MQuestion>":
								  List<String> question =new ArrayList<String>();
								  List<String> choices =new ArrayList<String>();
								  int answer=0;
								  while(!s.equals("</MQuestion>"))
								  {
								  	s=fin.readLine();
								  	switch(s)
								  	{
									  	case"<question>":
										  		s=fin.readLine();
										  		
										  		while(!s.equals("</question>"))
										  		{	 			
										 			question.add(s);
										  			s=fin.readLine();
												}
												break;
										case  "<answer>":
												s=fin.readLine();
												while(!s.equals("</answer>"))
												{		
													answer=Integer.parseInt(s);
										  			s=fin.readLine();
												}
												break;
									
										case "<choices>":
												s=fin.readLine();
												while(!s.equals("</choices>"))
												{	
													choices.add(s);
										  			s=fin.readLine();
												}
												break;
									}	
								  }
								  MultipleChoiceQuestion mq = new MultipleChoiceQuestion(question,choices,answer);
								  quiz.add(mq);
								  break;
						case "<TFQuestion>":
								  List<String> _question =new ArrayList<String>();
								  String _answer=null;
								  while(!s.equals("</TFQuestion>"))
								  {
								  	s=fin.readLine();
								  	switch(s)
								  	{
									  	case"<question>":
										  		s=fin.readLine();
										  		while(!s.equals("</question>"))
										  		{	
										  			_question.add(s);
										  			s=fin.readLine();
												}
												break;
										case  "<answer>":
												s=fin.readLine();
												while(!s.equals("</answer>"))
												{	
													_answer=s;
										  			s=fin.readLine();
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
				fin.close();
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
		
			for(Iterator<Question> i =quiz.iterator();i.hasNext();)
			{
				Question q=i.next();
				if(!addQuestion(q))
					return false;
			}	
		
			conn.commit();
		
		}catch (FileNotFoundException e1) 
		{
			// If this file does not exist
			System.err.println("File not found !!!");
			return false;
		}
		catch (Throwable e)  
		{   
           	 	/*       Catch all exceptions and pass them to 
           		 **       the exception reporting method             */
            		System.out.println(" . . . (proerpties)exception thrown:");
            		errorPrint(e);
            		return false;
        	}
        	
		return true;
	}
	
	public int getTotalNumberOfQuestions()
	{
		int totalnumber=0;
		Statement s;
		
		try
		{	
			s= conn.createStatement();
		
			ResultSet rs=s.executeQuery("select count(*) from Questions");
			
			rs.next();
		
			totalnumber=rs.getInt(1);
			
			rs.close();
			
			s.close();
			
		}catch (Throwable e)  
		{   
           	 	/*       Catch all exceptions and pass them to 
           		 **       the exception reporting method             */
            		System.out.println(" . . .(number) exception thrown:");
            		errorPrint(e);
        	}
		
		return totalnumber;
	}
	
	public Question getQuestion(int questionIndex)
	{
		Question question=null;
	
		Statement st;
	
		try
		{	
			st= conn.createStatement();
		
			ResultSet rs = st.executeQuery("select * from Questions where Q_ID="+String.valueOf(questionIndex));
			
			if(rs.next())
			{
				/*       Get all information from a question by clob and seperate them into list for 
					storing into Question list 						*/
           		 		            
				Clob qText=rs.getClob(2);
				
				Clob qChoice=rs.getClob(3);
				
				int answer=rs.getInt(4);
				
				int qTextsize = (int)qText.length();
				
				int qChoicesize = (int)qChoice.length();
				
				List <String> q=Arrays.asList(qText.getSubString(1,qTextsize).split("\n"));
				
				List <String> c=Arrays.asList(qChoice.getSubString(1,qChoicesize).split("\n"));
			
				if(c.size()==2)
				{	
					if(answer==1)
						question = new TrueAndFalseQuestion(q, "True");
					else
						question = new TrueAndFalseQuestion(q, "False");
				}
				else
					question = new  MultipleChoiceQuestion(q, c, answer);		
			}
			else
				System.out.println("There is no data in " + questionIndex);
				
			rs.close();
			
			st.close();
			
		}catch (Throwable e) 
		{
         	/*       Catch all exceptions and pass them to 
           		 **       the exception reporting method             */
            		System.out.println(" . . . (getQuestion)exception thrown:");
            		errorPrint(e);
        }

		return question;
	}
	
	public boolean addQuestion(Question question)
	{
		PreparedStatement psInsert;

		Reader qReader;
		
		Reader cReader;
		
		try
		{	
			/*  Using IO Reader for putting them into clob format to storing question information into database  */
					
			psInsert = conn.prepareStatement("insert into Questions (question,choices,answer) values (?,?,?)");
			
			String qText=new String();
			
			for(Iterator q=question.getQuestion().iterator();q.hasNext();)
				qText+=q.next()+"\n";
			
			qReader=new StringReader(qText);
			
			psInsert.setClob(1,qReader);			
			
			String cText=new String();
			
			for(Iterator c=question.getChoices().iterator();c.hasNext();)
				cText+=c.next()+"\n";
			
			cReader=new StringReader(cText);
			
			psInsert.setClob(2,cReader);			
			
			psInsert.setInt(3,question.getAnswer());
			
			psInsert.executeUpdate();
			
			psInsert.close();
			
		}catch(Throwable e) 
		{
         	/*       Catch all exceptions and pass them to 
           		 **       the exception reporting method             */
            		System.out.println(" . . . (add)exception thrown:");
            		errorPrint(e);
            		return false;
        	}	
			
		return true;
	}
	
	public boolean cleanUpDB()
	{
		Statement s;
		
		try
		{
			s= conn.createStatement();
			
			s.executeUpdate("DROP TABLE Questions");
		
			//s.executeUpdate("DROP DATABASE JavaQuestionDB");
			
			s.close();
			
			conn.close();
			
		}catch(Throwable e)  
		{   
           	 	/*       Catch all exceptions and pass them to 
           		 **       the exception reporting method             */
            		System.out.println(" . . . (clean)exception thrown:");
            		errorPrint(e);
        	}
		
		return true;
	}
	
	public List<Question> makeQuiz(int noOfQuestions)
	{
		int total=getTotalNumberOfQuestions();
		
		Random rand=new Random();
		
		boolean[] repeat=new boolean[total];
		
		int num=0;
		
		List<Question> Quiz=new ArrayList<Question>();
		
		for (int i=0;i<noOfQuestions;i++)
		{
			do
			{
				num=rand.nextInt(total-1)+1;
			}while(repeat[num]);
			
			repeat[num]=true;//make sure the random number is unique.
			
			Quiz.add(getQuestion(num));
		}
			
		return Quiz;
	}
	
	public static void errorPrint(Throwable e) 
	{
        	if (e instanceof SQLException) 
            		SQLExceptionPrint((SQLException)e);
         	else
         	{
            		System.out.println("A non SQL error occured.");
            		e.printStackTrace();
         	}   
      	}       // END errorPrint 

    		//  Iterates through a stack of SQLExceptions 
      	public static void SQLExceptionPrint(SQLException sqle) 
      	{
        	while (sqle != null)
        	{
		    System.out.println("\n---SQLException Caught---\n");
		    System.out.println("SQLState:   " + (sqle).getSQLState());
		    System.out.println("Severity: " + (sqle).getErrorCode());
		    System.out.println("Message:  " + (sqle).getMessage()); 
		    sqle.printStackTrace();  
		    sqle = sqle.getNextException();
		}
   	}	//  END SQLExceptionPrint 
}