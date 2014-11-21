import java.util.List;

import au.edu.uow.QuestionLibrary.*;
import au.edu.uow.UserInterface.*;

/**
 * This is the application that tests students with Java quiz.<br>
 * This program is provided for the CSCI213 Assignment. <br>
 * Note: You should not modify this program. This program will use
 *       classes you created as instructed in the Assignment paper.
 * 
 * @author Yi Jin, 4370673, yj742
 *
 */
public class JavaQuiz {

	/**
	 * This constant specifies the number of questions in each quiz.
	 */
	final private static int NoOfQuestions = 5;

	/**
	 * This is the entry point of the application
	 * @param args Command line options.
	 */
	public static void main(String[] args){

		if(args.length == 1){
			
			/* You should create the QuestionLibrary class */
			boolean isQuestionLibraryReady = QuestionLibrary.buildLibrary(args[0]);
			if (isQuestionLibraryReady == true) {
				System.out.println("===== Java Quiz, v1.0 =====\n");
				/* You should create the UserInterface class */
				UserInterface ui = new UserInterface();

				System.out.println("-- Student login --");		
				/* You should create the Student class */
				Student student = ui.getStudent();

				System.out.println("\n-- Quiz begins --");
				List<Question> quiz = QuestionLibrary.makeQuiz(NoOfQuestions);
				ui.startQuiz(quiz, student);

				System.out.println("\n-- Student marks  --");
				ui.showStudentMarks(student);
				
			}else{
				System.err.println("Error: failed to build a question library. Exiting ...");
				System.exit(0);
			}
		}else{
			System.err.println("Usage: java JavaQuiz questionFileName");
		}
	}
}
