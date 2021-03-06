package au.edu.uow.QuestionLibrary;

import java.io.*;
import java.util.*;
/**
 * This is the class that show the methods to store and display multiple questions.<br>
 * @author Yi Jin, 4370673, yj742
 *
 */
public class MultipleChoiceQuestion implements Question,Serializable
{
	private List<String> MQuestion; 
	
	private int MAnswer;
	
	private List<String> choices;
	/**
	 * This is the constructor of the class
	 * @see #getQuestion()
	 * @see #getChoices()
	 * @see #compareAnswer(int)
	 * @param MQuestion The multiple choices questions,ans Answer,choices The options
	 **/
	public MultipleChoiceQuestion(List<String> MQuestion,int ans,List<String> choices)
	{
		this.MQuestion=MQuestion;	
		MAnswer=ans;
		this.choices=choices;
	}
	/**
	 * This is get the Question list 
	 * @see #MultipleChoiceQuestion(List<String>,int,List<String>)
	 * @see #getChoices()
	 * @see #compareAnswer(int)
	 **/
	public List<String> getQuestion()
	{
		return MQuestion;
	}
	/**
	 * This is get the Choices list 
	 * @see #MultipleChoiceQuestion(List<String>,int,List<String>)
	 * @see #getQuestion()
	 * @see #compareAnswer(int)
	 **/
	public List<String> getChoices()
	{
		return choices;
	}
	/**
	 * This is compareAnswer 
	 * @see #MultipleChoiceQuestion(List<String>,int,List<String>)
	 * @see #getQuestion()
	 * @see #getChoices()
	 * @param ans The answer
	 **/
	public boolean compareAnswer(int ans)
	{
		if(ans==MAnswer)
			return true;
		else
			return false;
	}
	
}