package au.edu.uow.Networking;

import java.io.*;
import java.net.*;
import java.util.*;
import au.edu.uow.ClientGUI.*;
import au.edu.uow.QuestionLibrary.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment. <br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class JavaServerHandler
{
	private List<Question> quiz;
	
	public JavaServerHandler(Student student,Socket socket)
	{
		try
		{
			InputStream iS=socket.getInputStream();
			OutputStream oS=socket.getOutputStream();
			PrintWriter out=new PrintWriter(oS,true);
			
			out.println("REGISTER");
			out.println(student.getName());
		
			Scanner in=new Scanner(iS);
			String scan=in.nextLine();

			if(scan.equals("OK"))
			{
				out.println("GETQUESTION");
				ObjectInputStream getFromServer=new ObjectInputStream(socket.getInputStream());
				//get information from server
				Object object=getFromServer.readObject();
				//cast the object and assign to the quiz
				quiz=(List<Question>)object;
			}
		}
		catch (Exception e)
		{
			System.out.println("Error"+e);
		}
	}
	
	public List<Question> getQuiz()
	{
		return quiz;
	}
}
