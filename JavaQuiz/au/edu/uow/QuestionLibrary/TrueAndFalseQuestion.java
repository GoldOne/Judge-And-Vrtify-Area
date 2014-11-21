package au.edu.uow.QuestionLibrary;

import java.io.*;
import java.util.*;
/**
 * This is the class that show the methods to store and display TF questions.<br>
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class TrueAndFalseQuestion implements Question,Serializable
{
	private List<String> TFQuestion; 
	
	private String TFAnswer;
	/**
	 * This is the constructor of the class
	 * @see #getQuestion()
	 * @see #getChoices()
	 * @see #compareAnswer(int)
	 * @param TFQuestion The true or false questions,ans Answer
	 **/
	public TrueAndFalseQuestion(List<String> TFQuestion,String ans)
	{
		this.TFQuestion=TFQuestion;	
		TFAnswer=ans;	
	}
	/**
	 * This is get the Question list 
	 * @see #TrueAndFalseQuestion(List<String>,String)
	 * @see #getChoices()
	 * @see #compareAnswer(int)
	 **/
	public List<String> getQuestion()
	{
		return TFQuestion;
	}
	/**
	 * This is get the Choices list 
	 * @see #TrueAndFalseQuestion(List<String>,String)
	 * @see #getQuestion()
	 * @see #compareAnswer(int)
	 **/
	public List<String> getChoices()
	{
		List<String> choice =new ArrayList<String>();
		choice.add("True");
		choice.add("False");
		return choice;
	}
	/**
	 * This is compareAnswer 
	 * @see #TrueAndFalseQuestion(List<String>,String)
	 * @see #getQuestion()
	 * @see #getChoices()
	 * @param ans The answer
	 **/
	public boolean compareAnswer(int ans)
	{
		switch(ans)
		{
			case 1:
				String an="true";
				if(an.equalsIgnoreCase(TFAnswer))
					return true;
				else
					return false;
			case 2:
				String er="flase";
				if(er.equalsIgnoreCase(TFAnswer))
					return true;
				else
					return false;
		}
		return false;
	}

}