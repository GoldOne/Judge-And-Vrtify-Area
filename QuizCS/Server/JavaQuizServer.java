import java.io.*;
import java.net.*;
import au.edu.uow.Networking.*;
import au.edu.uow.QuestionLibrary.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment 4. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class JavaQuizServer
{  
	private static int port =40213; 

	public static void main(String[] args)
	{  
		if(args.length==1)
			port=Integer.parseInt(args[0]);
			
		if(QuestionLibrary.buildLibrary("questions.xml")==false)
		{
			System.err.println("Error: failed to build a question library. Exiting ...");
			System.exit(0);
		}
		try 
		{
			// display server information
			System.out.println("JavaQuizServer listening at: "+port);

			ServerSocket serverSocket=new ServerSocket(port);
			
			while(true)
			{
				Socket incoming=serverSocket.accept();
				
				Runnable clientHandler=new JavaClientHandler(incoming);
				
				new Thread(clientHandler).start();//make new thread for a client
			}
		}
		catch (IOException e)
		{  
			e.printStackTrace();
		}
	}
}
