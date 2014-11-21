package au.edu.uow.QuestionLibrary;

import java.util.List;

/**
 * This interface specifies the requirements of implementation classes.<br>
 * 
 * This program is provided for the CSCI213 Assignment. <br>
 * Note: You should not modify this program but create suitable
 *       classes to implement this interface.
 *       
 * @author Yi Jin
 * 
 */
public interface Question {
	
	/**
	 * This method returns the question text.
	 * @return The question text in a list
	 * @see #getChoices()
	 * @see #compareAnswer(int)
	 */
	List<String> getQuestion();
	
	/**
	 * This method returns the multiple choices. 
	 * @return The list of choices
	 * @see #getQuestion()
	 * @see #compareAnswer(int)
	 */
	List<String> getChoices();
	
	/**
	 * This method compares the student's answer to the standard answer. 
	 * @see #getQuestion()
	 * @see #getChoices()
	 * @param ans The student's answer
	 * @return True for the correct answer; false for incorrect answers.
	 */
	boolean compareAnswer(int ans);
}
