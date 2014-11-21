package au.edu.uow.UserInterface;

import java.util.*;

import au.edu.uow.QuestionLibrary.*;
/**
 * This is class that present the process of the whole quiz.<br>
 * @author Yi Jin, 4370673, yj742
 *
 */
public class UserInterface 
{	
	/**
	 * This is creating a new student. 
	 **/
	Student student= new Student();
	/**
	 * This is return student's name to student.
	 * @see #startQuiz(List<Question>, Student)
	 * @see #showStudentMarks(Student)
	 * @return The student's name to student 
	 **/
	public Student getStudent()
	{		
		System.out.print("Your name: ");
	
		Scanner scan =new Scanner(System.in);	
		
		String newname=scan.nextLine();
		
		student.setName(newname);
		
		return student;
	}	
	/**
	 * This is starting the quiz and listing questions and choices.
	 * @see #getStudent()
	 * @see #showStudentMarks(Student)
	 * @param quiz The question list, student Student himself
	 **/
	public void startQuiz(List<Question> quiz, Student student)
	{	
		int count=0;
		int j=1;
		for(Iterator<Question> i = quiz.iterator();i.hasNext();)
		{
			System.out.println();
			Question q=i.next();
			System.out.println("Question No "+j+":");
			System.out.println();
			for(Iterator a=q.getQuestion().iterator();a.hasNext();)
			{
				System.out.println(a.next());
			}
			System.out.println();
			int A=1;
			for(Iterator b=q.getChoices().iterator();b.hasNext();)
			{
				System.out.println(A+": "+b.next());
				A++;
			}
			System.out.println();
			System.out.print("Choose your answer: ");
			try
			{
				Scanner scan =new Scanner(System.in);
				int choose=scan.nextInt();
				student.recordScore((q.compareAnswer(choose)));
			}
			catch (Exception e)
			{
				System.out.println("Error!!! Not a number!!!");
			}
			j++;
			count++;
			if(count==5)
				break;
		}
	}
	/**
	 * This is show the final result of quiz.
	 * @see #getStudent()
	 * @see #startQuiz(List<Question>, Student)
	 * @param student Student himself
	 **/
	public void showStudentMarks(Student student)
	{
		System.out.println("Result of "+student.getName()+": "+student.getScore()+" Out of 5");
	}
	
}