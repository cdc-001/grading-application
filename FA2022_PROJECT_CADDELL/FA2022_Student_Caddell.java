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
	public FA2022_Student_Caddell(String cn, String sn, String id, int ex, String[] an, int[] ss, String[] sss)
	{
		courseName = cn;
		studentName = sn;
		studentID = id;
		extraCreditScore = ex;
		
		for(int i = 0; i < ARRAY_SIZE; i++)
		{
			assignmentNames[i] = an[i];
			studentScores[i] = ss[i];
			studentScoresString[i] = sss[i];
		}
		
	}
}
