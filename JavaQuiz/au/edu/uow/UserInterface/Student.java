package au.edu.uow.UserInterface;
/**
 * This is the class that show the methods to crate Student, store name and record scores.<br>
 * @author Yi Jin, 4370673, yj742
 *
 */
public class Student
{
	private String name;
	
	private static int score=0;
	/**
	 * This is constructor of Student
	 * @see #setName(String)
	 * @see #getName()
	 * @see #recordScore(boolean)
	 * @see #getScorce()
	 **/
	/*public Student (String studentname)
	{
		name=studentname;	
	}*/
	public Student()
	{
		
	}
	/**
	 * This is get the name of Student
	 * @see #Student()
	 * @see #getName()
	 * @see #recordScore(boolean)
	 * @see #getScorce()
	 * @param name The name of Student
	 **/
	public void setName(String name)
	{
		this.name=name;
	}
	/**
	 * This is display the name of Student
	 * @see #Student()
	 * @see #setName(String)
	 * @see #recordScore(boolean)
	 * @see #getScorce()
	 **/
	public String getName()
	{
		return name;
	}
	/**
	 * This is record the score of Student
	 * @see #Student()
	 * @see #setName(String)
	 * @see #getName()
	 * @see #getScorce()
	 * @param isCorrect whether the answer is correct or not
	 **/
	public void recordScore(boolean isCorrect)
	{
		if(isCorrect)
			score++;
	}
	/**
	 * This is display the score of Student
	 * @see #Student()
	 * @see #setName(String)
	 * @see #getName()
	 * @see #recordScore(boolean)
	 **/
	public int getScore()
	{
		return score;
	}
}
