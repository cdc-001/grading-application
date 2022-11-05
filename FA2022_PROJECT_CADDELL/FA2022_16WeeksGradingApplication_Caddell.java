//FA2022_16WeeksGradingApplication_Caddell.java

import java.util.Scanner;

public class FA2022_16WeeksGradingApplication_Caddell 
{
	public static void main(String[] args)
	{
		final int ARRAY_SIZE = 7;
		
		String[] assignmentNames = {"Quiz", "Homework", "Lab", "Project", "Teamwork", "Topic", "Test"};
		int[][] assignmentScores = new int[ARRAY_SIZE][];
		float[] maxScores = new float[ARRAY_SIZE];
		
		String courseName,
			   studentID,
			   studentName;
		int selection;					//Menu selection
		
		do
		{
			//Display menu to screen
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "LIST OF ASSIGNMENTS  – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n"
							 + "1.QUIZ\n2.HOMEWORK\n3.LAB\n4.PROJECT\n5.TEAMWORK6.TOPIC\n7.TEST\n0.EXIT\n");
			
			//Read input from user
			Scanner keyboard = new Scanner(System.in);
			selection = keyboard.nextInt();
			
			
			
		}while(selection !=0);
		
		System.out.println("Thank you! Have a nice day!")
	}
}
