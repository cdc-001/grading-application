//FA2022_16WeeksGradingApplication_Caddell.java

import java.util.Scanner;

public class FA2022_16WeeksGradingApplication_Caddell 
{
	public static void main(String[] args)
	{
		final int ARRAY_SIZE = 7;
		
		String[] assignmentNames = {"Quiz", "Homework", "Lab", "Project", "Teamwork", "Topic", "Test"};			//Assignment categories
		int[] assignmentSizes = new int[ARRAY_SIZE];															//How many assignments within each assignment category
		float[] maxScores = new float[ARRAY_SIZE];
		int[][] assignmentScores = new int[ARRAY_SIZE][];
		String[] studentScoresString = new String[ARRAY_SIZE];
		
		String courseName,
			   studentID,
			   studentName;
		int selection;																							//Menu selection
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
							 + "1.QUIZ\n2.HOMEWORK\n3.LAB\n4.PROJECT\n5.TEAMWORK\n6.TOPIC\n7.TEST\n0.EXIT");
			
			//Read input from user
			selection = keyboard.nextInt();
			System.out.println();
			
			while(selection < 0 || selection > 7)
			{
				System.out.print("Invalid selection.  Please try again. ");
				selection = keyboard.nextInt();
			}
			
			confirm = 'n';
			if (selection > 0)
			{
				System.out.print("How many " + assignmentNames[selection - 1] + " scores? ");
				assignmentSizes[selection - 1] = keyboard.nextInt();
				
				System.out.print("Enter maximum score for each " + assignmentNames[selection - 1] + " assignment: "); 
				maxScores[selection - 1] = keyboard.nextInt() * assignmentSizes[selection - 1];
				
				System.out.println();
			}
			else
			{
				//User confirm input is correct
				System.out.println("Confirm the following is correct: ");
				for (int i = 0; i < ARRAY_SIZE; i++)
				{
					System.out.println("  " + 
									   String.format("%-10s%-10s", assignmentNames[i] + ": ", "Total assignments = " 
									 + assignmentSizes[i] + "; Maximum score = " + maxScores[i]));
				}
				System.out.print("Enter \"Yes\" to proceed or \"No\" to return to main menu: ");
				keyboard.nextLine();
				input = keyboard.nextLine();
				input.toLowerCase();
				confirm = input.charAt(0);
				
				while(confirm != 'y' && confirm != 'n')
				{
					System.out.print("Invalid input.\n"
									+ "Enter \"Yes\" to proceed or \"No\" to return to main menu: ");
					input = keyboard.nextLine();
					confirm = input.charAt(0);
				}
			}
		}while(confirm != 'y');
				
		do
		{
			System.out.println("\nFA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "TASK OF GRADING – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n"
							 + "1.Grading One Student\n"
							 + "2.Printing the Grade of One Studen from input file\n"
							 + "3.Printing the Grades of Class\n"
							 + "0.Exit");
			selection = keyboard.nextInt();
			
			switch (selection)
			{
				case 0:
					break;
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid selection.");
			}
		}while(selection != 0);
		
		System.out.println("Thank you! Have a nice day!");
	}

	
	/*
	public static void readStudentScores(int select, String[] names, int[][] score, Scanner k)
	{
		int num;
		System.out.print("How many " + names[select - 1] + " scores? ");
		num = k.nextInt();
		
		size[select -1] = new int[max];
		
		for (int i = 0; i < max; i++)
		{
			System.out.print("Enter your score for " + names[select - 1] + " " + (i + 1) + ": "); 
			scores[select - 1][i] = k.nextInt();
		}
	}
	*/
	
	/*
	 * Method to combine assignment names and scores into string
	 * @param names assignment names array
	 * @param scores assignment score array
	 * @param toString student scores string array
	 
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
	*/
}
