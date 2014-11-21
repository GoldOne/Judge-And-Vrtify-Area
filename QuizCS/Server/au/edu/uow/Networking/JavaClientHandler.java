package au.edu.uow.Networking;

import java.io.*;
import java.net.*;
import java.util.*;
import au.edu.uow.QuestionLibrary.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment 4. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class JavaClientHandler implements Runnable
{
	private Socket incoming;
	private static List<Question> quiz;
	
	public JavaClientHandler(Socket i)
	{
		incoming=i;
	}

	public void run()
	{
		try
		{	
			InputStream inStream=incoming.getInputStream();
			Scanner scan=new Scanner(inStream);
			OutputStream outStream=incoming.getOutputStream();     
			PrintWriter out=new PrintWriter(outStream,true);
			String com=scan.nextLine();
			
			String name="";
			
			while(!com.equals("BYE"))
			{
				if(com.equals("REGISTER"))
				{
					com=scan.nextLine();
					name=com;
					System.out.println(com + " is registered");
					out.println("OK");
				}
				com=scan.nextLine();
				if(com.equals("GETQUESTION"))
				{
					quiz=QuestionLibrary.makeQuiz(5);
					ObjectOutputStream Client=new ObjectOutputStream(incoming.getOutputStream());
					Client.writeObject(quiz);
				}	
			}
			
			if(!name.equals(""))
				System.out.println(name+ " is disconnected");	
			scan.close();
			incoming.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("ERROR===========");
		}
	}
}
