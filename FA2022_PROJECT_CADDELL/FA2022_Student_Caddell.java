//FA2022_Student_Caddell.java

import java.io.*;

public class FA2022_Student_Caddell 
{
	private String courseName;									// Name of course
	private String studentID;									// Student ID number
	private String studentName;									// Name of student
	private float extraCreditScore;								// Score of extra credit assignment
	private float totalMaxScore;									// Cumulative score of all assignments
	
	private final int ARRAY_SIZE = 7;
	private String[] assignmentNames = new String[ARRAY_SIZE];		// Names of each assignment category
	private float[][] studentScores = new float[ARRAY_SIZE][];			// Cumulative score per assignment category
	private String[] studentScoresString = new String[ARRAY_SIZE];	// Combines scores and category category info.
	
	/** Method to create no-argument constructor */
	public FA2022_Student_Caddell()
	{
		courseName = "";
		studentName = "";
		studentID = "";
		extraCreditScore = 0;
		totalMaxScore = 0;
	}
	
	/** 
	 * Method to create parameterized constructor
	 * @param cn course name
	 * @param sn student name
	 * @param id student ID number
	 * @param ex extra credit score
	 * @param an assignment name array
	 * @param ss assignment score array
	 * @param sss combined assignment score and name string
	 */
	public FA2022_Student_Caddell(String cn, String id, String sn, float ex, String[] an, float[][] ss, String[] sss)
	{
		courseName = cn;
		studentID = id;
		studentName = sn;
		extraCreditScore = ex;
		
		for(int row = 0; row < ARRAY_SIZE; row++)
		{
			assignmentNames[row] = an[row];
			studentScoresString[row] = sss[row];
			
			for (int col = 0; col < ss[row].length; col++)
			{
				studentScores[row] = ss[col];
			}
		}	
	}
	
	/** Method to calculate cumulative total max score of all standard assignments */
	public float calcTotalMaxScore()
	{
		totalMaxScore = 0;
		for (int row = 0; row < studentScores.length; row++)
		{
			for (int col = 0; col < studentScores[row].length; col++)
			{
				totalMaxScore += studentScores[row][col];
			}
		}
		return totalMaxScore;
	}
	
	/** 
	 * Method to calculate total student score
	 * @return  sum of total max score and extra credit score
	*/
	public float calcTotalStudentScores()
	{
		return calcTotalMaxScore() + extraCreditScore;
	}
	
	/**
	 * Method to calculate numeric grade
	 * @return total student score multiplied by 100 and divided by total max score
	 */
	public float calcNumericGrade()
	{
		return 100 * calcTotalStudentScores() / calcTotalMaxScore();
	}
	
	/**
	 * Method to determine letter grade
	 * @return Letter grade A - F
	 */
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
		
		outputFile.print(courseName + ", " + studentID + ", " + studentName + ", "
						+ String.format("%.2f", calcNumericGrade()) + ", " 
						+ determineLetterGrade());
		
		for(int row = 0; row < studentScores.length; row++)
		{
			for (int col = 0; col < studentScores[row].length; col++)
			{
				outputFile.print(", " + String.format("%.2f", studentScores[row][col]));
			}
		}
		outputFile.println();
		
		outputFile.close();
	}
	
	/** Method to display minimum student information to screen */
	public String shortOutput()
	{
		return 	String.format("%-21s%15s\n", "STUDENT ID:", studentID)
			  + String.format("%-21s%15s\n", "NAME:", studentName)
			  + String.format("%-21s%15.2f\n", "Numeric Grade:", calcNumericGrade())
			  + String.format("%-21s%15s\n", "Letter Grade:", determineLetterGrade());
	}
	
	/**
	 * Method to display detailed student information via toString to screen
	 * @return Report showing all grades associated with each assignment category
	 */
	public String toString()
	{
		return "FA2022_16WeekGradingApplication_Caddell.java\n"
			 + "FINAL GRADE OF STUDENT - CORY CADDELL\n"
			 + "--------------------------------------------------------------------------------------\n"
			 + String.format("%-21s%-15s\n", "COURSE NAME:", courseName)
			 + String.format("%-21s%-15s\n", "STUDENT ID:", studentID)
			 + String.format("%-21s%-15s\n", "NAME:", studentName)
			 + "--------------------------------------------------------------------------------------\n"
			 + String.format("%-21s%-15.2f\n", "POLICY QUIZ:", extraCreditScore)
			 + String.format("%-21s%-15s\n", assignmentNames[0], studentScoresString[0])
			 + String.format("%-21s%-15s\n", assignmentNames[1], studentScoresString[1])
			 + String.format("%-21s%-15s\n", assignmentNames[2], studentScoresString[2])
			 + String.format("%-21s%-15s\n", assignmentNames[3], studentScoresString[3])
			 + String.format("%-21s%-15s\n", assignmentNames[4], studentScoresString[4])
			 + String.format("%-21s%-15s\n", assignmentNames[5], studentScoresString[5])
			 + String.format("%-21s%-15s\n", assignmentNames[6], studentScoresString[6])
			 + "--------------------------------------------------------------------------------------\n"
			 + String.format("%-21s%7.2f\n", "Total STUDENT Score:", calcTotalStudentScores())
			 + String.format("%-21s%7.2f\n", "Total MAX score:", totalMaxScore)
			 + String.format("%-21s%7.2f\n", "Numeric Grade:", calcNumericGrade())
			 + String.format("%-21s%7s\n", "Letter Grade:", determineLetterGrade())
			 + "--------------------------------------------------------------------------------------\n";
	}
}
