//FA2022_16WeeksGradingApplication_Caddell.java

import java.util.Scanner;

public class FA2022_16WeeksGradingApplication_Caddell 
{
	public static void main(String[] args)
	{
		final int ARRAY_SIZE = 7;
		
		String[] assignmentNames = {"Quiz", "Homework", "Lab", "Project", "Teamwork", "Topic", "Test"};
		int[][] assignmentScores = new int[ARRAY_SIZE][];
		String[] studentScoresString = new String[ARRAY_SIZE];
		
		String courseName,
			   studentID,
			   studentName;
		int selection;					//Menu selection
		String input;
		char confirm;
		boolean debug = true;
				//debug = false;
		
		Scanner keyboard = new Scanner(System.in);
				
		do
		{
			//Display menu to screen
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "LIST OF ASSIGNMENTS  – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n"
							 + "1.QUIZ\n2.HOMEWORK\n3.LAB\n4.PROJECT\n5.TEAMWORK6.TOPIC\n7.TEST\n0.EXIT");
			
			//Read input from user
			selection = keyboard.nextInt();
			
			switch (selection)
			{
				case 0:
					break;
				case 1:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				case 2:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				case 3:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				case 4:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				case 5:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				case 6:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				case 7:
					readScores(selection, assignmentNames, assignmentScores, keyboard);
					break;
				default:
					System.out.println("Invalid selection.");
					break;
			}
			
			confirm = 'n';
			if(selection == 0)
			{
				studentScoresToString(assignmentNames, assignmentScores, studentScoresString);
				System.out.println("Confirm your scores are correct: ");
				for (String scores : studentScoresString)
				{
					System.out.println("  " + scores);
				}
				System.out.print("Yes or No: ");
				keyboard.nextLine();
				input = keyboard.nextLine();
				input.toLowerCase();
				confirm = input.charAt(0);
				
				while(confirm != 'y' && confirm != 'n')
				{
					System.out.print("Invalid input.\n"
									+ "Yes or No: ");
					input = keyboard.nextLine();
					confirm = input.charAt(0);
				}
			}
	
		}while(confirm != 'y');
				
		do
		{
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "TASK OF GRADING – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n"
							 + "1.Grading One Student\n"
							 + "2.Printing the Grade of One Studen from input file\n"
							 + "3.Printing the Grades of Class\n"
							 + "0.Exit");
			selection = keyboard.nextInt();

			
		}while(selection != 0);
		
		System.out.println("Thank you! Have a nice day!");
	}
	
	/*
	 * Method to read and store assignment scores in the respective row of assignment array
	 * @param select User assignment selection
	 * @param names Assignment names
	 * @param scores Assignment score array
	 * @param k Scanner class, keyboard object
	 */
	public static void readScores(int select, String[] names, int[][] scores, Scanner k)
	{
		int max;
		System.out.print("How many " + names[select - 1] + " scores? ");
		max = k.nextInt();
		
		scores[select -1] = new int[max];
		
		for (int i = 0; i < max; i++)
		{
			System.out.print("Enter your score for " + names[select - 1] + " " + (i + 1) + ": "); 
			scores[select - 1][i] = k.nextInt();
		}
	}
	
	/*
	 * Method to combine assignment names and scores into string
	 * @param names assignment names array
	 * @param scores assignment score array
	 * @param toString student scores string array
	 */
	public static void studentScoresToString(String[] names, int[][] scores, String[] toString)
	{
		for (int row = 0; row < names.length; row++)
		{
			toString[row] = names[row] + ":";
			for (int col = 0; col < scores[row].length; col++)
			{
				toString[row] += ((col > 0) ? ", " : " ") + scores[row][col]; 
			}
		}
	}
}
