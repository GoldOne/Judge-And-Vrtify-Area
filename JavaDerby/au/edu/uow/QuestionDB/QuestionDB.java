package au.edu.uow.QuestionDB;

import java.util.List;
import au.edu.uow.QuestionLibrary.*;

/**
 * This interface specifies the requirements of implementation classes 
 * 
 * This program is provided for the CSCI213 Assignment. <br>
 * Note: You should not modify this program but create a suitable
 *       class to implement this interface.
 *       
 * @author Lei Ye
 * @version v.1.0
 *
 */
public interface QuestionDB {
	
	/**
	 * This method creates and populates a table in the database 
	 *   storing the questions from the question file. This method 
	 *   should use the addQuestion method to insert question content 
	 *   into the database
	 * @param questionFile The file name of the question file
	 * @return True if the database is successfully populated
	 * @see #addQuestion(Question)
	 */
	boolean buildQuestionDB(String questionFile);
	
	/**
	 * This method returns the total number of questions in the database
	 * @return The total number of questions in the question database
	 * @see #buildQuestionDB
	 */
	int getTotalNumberOfQuestions();

	/**
	 * This method returns the question from the database at the given position
	 * @param questionIndex The index of the question in the database
	 * @return The question object
	 */
	Question getQuestion(int questionIndex);
	
	/**
	 * This method adds a question to the database
	 * @param question The question object to be added to the database
	 * @return True if the operation is successful
	 */
	boolean addQuestion(Question question);
		
	/**
	 * This method removes the created table from the database
	 * @return True if the operation is successful
	 * @see #buildQuestionDB(String)
	 */
	boolean cleanUpDB();

	/**
	 * This method makes a quiz from the question database 
	 *   with the number of questions as specified. This method
	 *   should use the getQuestion method to retrieval question
	 *   content from the database
	 * @param noOfQuestions - the number of questions in a quiz
	 * @return Quiz questions in a list
	 * @see #getQuestion(int)
	 */
	List<Question> makeQuiz(int noOfQuestions);
	
}