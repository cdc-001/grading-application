//FA2022_Student_Caddell.java

import java.io.*;

public class FA2022_Student_Caddell 
{
	private String courseName;										// Name of course
	private String studentID;										// Student ID number
	private String firstName;										// First name of student
	private String lastName;										// Last name of student
	private float extraCreditScore;									// Score of extra credit assignment, policy quiz
	
	private final int ARRAY_SIZE = 7;								// Maximum amount of assignment categories, per program requirements
	private String[] assignmentNames = new String[ARRAY_SIZE];		// Names of each assignment category
	private float[][] studentScores = new float[ARRAY_SIZE][];		// Cumulative score per assignment category
	private String[] studentScoresString = new String[ARRAY_SIZE];	// Combines scores and category category info.
	private float[] maxScores = new float[ARRAY_SIZE];				// Cumulative maximum score per assignment category 
	
	/** Method to create no-argument constructor */
	public FA2022_Student_Caddell()
	{
		courseName = "";
		firstName = "";
		lastName = "";
		studentID = "";
		extraCreditScore = 0.0f;
	}
	
	/** Method to create parameterized constructor	 */
	public FA2022_Student_Caddell(String cn, String id, String fn, String ln, float ex, String[] an, float[][] ss, String[] sss, float[] ms)
	{
		courseName = cn;
		firstName = fn;
		lastName = ln;
		studentID = id;
		extraCreditScore = ex;
		
		for(int row = 0; row < an.length; row++)
		{
			assignmentNames[row] = an[row];
			studentScoresString[row] = sss[row];
			maxScores[row] = ms[row];
			
			studentScores[row] = new float[ss[row].length];		// Set the columns for ragged array.
			for (int col = 0; col < ss[row].length; col++)
			{
				studentScores[row][col] = ss[row][col];
			}
		}	
	}
	
	/** Method to calculate cumulative possible score of all standard assignments.	 */
	public float calcTotalMaxScore()
	{
		float totalMaxScore = 0.0f;
		
		for (int row = 0; row < maxScores.length; row++)
		{
			totalMaxScore += maxScores[row];
		}
		
		return totalMaxScore;
	}
	
	/** Method to calculate cumulative student score of all assignments	 */
	public float calcTotalStudentScore()
	{
		float totalStudentScore = 0.0f;
		
		for (int row = 0; row < studentScores.length; row++)
		{
			for (int col = 0; col < studentScores[row].length; col++)
			{
				totalStudentScore += studentScores[row][col];
			}
		}	
		
		totalStudentScore += extraCreditScore;
		
		return totalStudentScore;
	}
	
	/** Method to calculate numeric grade percentage	 */
	public float calcNumericGrade()
	{
		return 100 * calcTotalStudentScore() / calcTotalMaxScore();
	}
	
	/** Method to determine letter grade	 */
	public char determineLetterGrade()
	{
		if (calcNumericGrade() >= 90.0)
		{
			return 'A';
		}
		else if(calcNumericGrade() >= 80.0)
		{
			return 'B';
		}
		else if(calcNumericGrade() >= 70.0)
		{
			return 'C';
		}
		else if(calcNumericGrade() >= 60.0)
		{
			return 'D';
		}
		else
		{
			return 'F';
		}
	}
	
	/** Method to write student information to output file */
	public void toFile() throws IOException
	{
		FileWriter fwriter = new FileWriter("student-grades.txt", true);
		PrintWriter outputFile = new PrintWriter(fwriter);
		
		outputFile.print(courseName + "," + studentID + "," + lastName + "," + firstName
						+ String.format(",%.2f", calcNumericGrade()) + "," 
						+ determineLetterGrade() + "," + extraCreditScore);
		
		for(int row = 0; row < studentScores.length; row++)
		{
			for (int col = 0; col < studentScores[row].length; col++)
			{
				outputFile.print(((col > 0) ? " " : ",") + String.format("%.2f", studentScores[row][col]));
			}
		}
		outputFile.println();
		
		outputFile.close();
	}
	
	/** Method to display minimum student information to screen	 */
	public String shortOutput()
	{
		return 	String.format("%-21s%15s\n", "STUDENT ID:", studentID)
			  + String.format("%-21s%15s\n", "NAME:", firstName + " " + lastName)
			  + String.format("%-21s%15.2f\n", "Numeric Grade:", calcNumericGrade())
			  + String.format("%-21s%15s\n", "Letter Grade:", determineLetterGrade());
	}
	
	/** Method to display detailed student information via toString to screen	 */
	public String toString()
	{
		return "FA2022_16WeekGradingApplication_Caddell.java\n"
			 + "FINAL GRADE OF STUDENT - CORY CADDELL\n"
			 + "----------------------------------------------------------------------------------------------------------\n"
			 + String.format("%-21s%-15s\n", "COURSE NAME:", courseName)
			 + String.format("%-21s%-15s\n", "STUDENT ID:", studentID)
			 + String.format("%-21s%-15s\n", "NAME:", firstName + " " + lastName)
			 + "----------------------------------------------------------------------------------------------------------\n"
			 + String.format("%-21s%-15.2f\n", "POLICY QUIZ:", extraCreditScore)
			 + String.format("%-21s%-15s\n", assignmentNames[0], studentScoresString[0])
			 + String.format("%-21s%-15s\n", assignmentNames[1], studentScoresString[1])
			 + String.format("%-21s%-15s\n", assignmentNames[2], studentScoresString[2])
			 + String.format("%-21s%-15s\n", assignmentNames[3], studentScoresString[3])
			 + String.format("%-21s%-15s\n", assignmentNames[4], studentScoresString[4])
			 + String.format("%-21s%-15s\n", assignmentNames[5], studentScoresString[5])
			 + String.format("%-21s%-15s\n", assignmentNames[6], studentScoresString[6])
			 + "----------------------------------------------------------------------------------------------------------\n"
			 + String.format("%-21s%7.2f\n", "Total STUDENT Score:", calcTotalStudentScore())
			 + String.format("%-21s%7.2f\n", "Total MAX score:", calcTotalMaxScore())
			 + String.format("%-21s%7.2f\n", "Numeric Grade:", calcNumericGrade())
			 + String.format("%-21s%7s\n", "Letter Grade:", determineLetterGrade())
			 + "----------------------------------------------------------------------------------------------------------\n";
	}
}
