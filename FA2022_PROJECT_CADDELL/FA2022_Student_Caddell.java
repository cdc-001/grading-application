//FA2022_Student_Caddell.java

public class FA2022_Student_Caddell 
{
	private String courseName;									// Name of course
	private String studentName;									// Name of student
	private String studentID;									// Student ID number
	private int extraCreditScore;								// Score of extra credit assignment
	private int totalMaxScore;									// Cumulative score of all assignments
	
	private final int ARRAY_SIZE = 7;
	private String[] assignmentNames = new String[ARRAY_SIZE];		// Names of each assignment category
	private int[] studentScores = new int[ARRAY_SIZE];				// Cumulative score per assignment category
	private String[] studentScoreString = new String[ARRAY_SIZE];	// Combines scores and category category info.
}
