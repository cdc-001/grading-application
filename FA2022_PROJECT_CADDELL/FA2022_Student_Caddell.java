//FA2022_Student_Caddell.java

import java.io.*;

public class FA2022_Student_Caddell 
{
	private String courseName;									// Name of course
	private String studentName;									// Name of student
	private String studentID;									// Student ID number
	private int extraCreditScore;								// Score of extra credit assignment
	private int totalMaxScore;									// Cumulative score of all assignments
	
	private final int ARRAY_SIZE = 7;
	private String[] assignmentNames = new String[ARRAY_SIZE];		// Names of each assignment category
	private int[][] studentScores = new int[ARRAY_SIZE][];			// Cumulative score per assignment category
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
	public FA2022_Student_Caddell(String cn, String sn, String id, int ex, String[] an, int[][] ss, String[] sss)
	{
		courseName = cn;
		studentName = sn;
		studentID = id;
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
	public void calcTotalMaxScore()
	{
		totalMaxScore = 0;
		for (int row = 0; row < studentScores.length; row++)
		{
			for (int col = 0; col < studentScores[row].length; col++)
			{
				totalMaxScore += studentScores[row][col];
			}
		}
	}
	
	/** 
	 * Method to calculate total student score
	 * @return  sum of total max score and extra credit score
	*/
	public int calcTotalStudentScores()
	{
		return totalMaxScore + extraCreditScore;
	}
	
	/**
	 * Method to calculate numeric grade
	 * @return total student score multiplied by 100 and divided by total max score
	 */
	public float calcNumericGrade()
	{
		return 100 * calcTotalStudentScores() / totalMaxScore;
	}
	
	/**
	 * Method to determine letter grade
	 * @return Letter grade A - F
	 */
	public char determineLetterGrade()
	{
		if (calcNumericGrade() >= 90)
		{
			return 'A';
		}
		else if(calcNumericGrade() >= 80)
		{
			return 'B';
		}
		else if(calcNumericGrade() >= 70)
		{
			return 'C';
		}
		else if(calcNumericGrade() >= 60)
		{
			return 'D';
		}
		else
		{
			return 'E';
		}
	}
	
	/** Method to write student information to output file */
	public void toFile() throws IOException
	{
		PrintWriter outputFile = new PrintWriter("student-grades.txt");
		
		outputFile.print(courseName + ", " + studentID + ", " + ", " + studentName 
						+ calcNumericGrade() + ", " + determineLetterGrade() + ",");
		
		for(int row = 0; row < studentScores.length; row++)
		{
			for (int col = 0; col < studentScores[row].length; col++)
			{
				outputFile.print(((col > 0) ? ", " : "") + studentScores[row][col]);
			}
		}
		
		outputFile.close();
	}
	
	/** Method to display minimum student information to screen */
	public String shortOutput()
	{
		return 	String.format("%-15s%15.2f\n", "Total STUDENT Score:", calcTotalStudentScores())
			  + String.format("%-15s%15.2f\n", "Total MAX score:", totalMaxScore)
		  	  + String.format("%-15s%15.2f\n", "Numeric Grade:", calcNumericGrade())
		  	  + String.format("%-15s%1sf\n", "Letter Grade:", determineLetterGrade());
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
			 + String.format("%-15s%-15s\n", "COURSE NAME:", courseName)
			 + String.format("%-15s%-15s\n", "STUDENT ID:", studentID)
			 + String.format("%-15s%-15s\n", "NAME:", studentID)
			 + "--------------------------------------------------------------------------------------\n"
			 + String.format("%-15s%-15s\n", "POLICY QUIZ:", courseName)
			 + String.format("%-15s%-15s\n", assignmentNames[0], studentScoresString[0])
			 + String.format("%-15s%-15s\n", assignmentNames[1], studentScoresString[1])
			 + String.format("%-15s%-15s\n", assignmentNames[2], studentScoresString[2])
			 + String.format("%-15s%-15s\n", assignmentNames[3], studentScoresString[3])
			 + String.format("%-15s%-15s\n", assignmentNames[4], studentScoresString[4])
			 + String.format("%-15s%-15s\n", assignmentNames[5], studentScoresString[5])
			 + String.format("%-15s%-15s\n", assignmentNames[6], studentScoresString[6])
			 + "--------------------------------------------------------------------------------------\n"
			 + String.format("%-15s%15.2f\n", "Total STUDENT Score:", calcTotalStudentScores())
			 + String.format("%-15d%15.2f\n", "Total MAX score:", totalMaxScore)
			 + String.format("%-15s%15.2f\n", "Numeric Grade:", calcNumericGrade())
			 + String.format("%-15s%15s\n", "Letter Grade:", determineLetterGrade())
			 + "--------------------------------------------------------------------------------------";
	}
}
