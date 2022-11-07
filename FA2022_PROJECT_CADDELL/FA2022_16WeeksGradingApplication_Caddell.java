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
		boolean debug = true;
				//debug = false;
				
		do
		{
			//Display menu to screen
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "LIST OF ASSIGNMENTS  – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n"
							 + "1.QUIZ\n2.HOMEWORK\n3.LAB\n4.PROJECT\n5.TEAMWORK6.TOPIC\n7.TEST\n0.EXIT");
			
			//Read input from user
			Scanner keyboard = new Scanner(System.in);
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
			
		}while(selection !=0);
		
		if (debug)
		{
			for (int row = 0; row < assignmentScores.length; row++)
			{
				for (int col = 0; col < assignmentScores[row].length; col++)
				{
					System.out.print(assignmentScores[row][col]);
				}
				System.out.println();
			}
		}
		
		System.out.println("Thank you! Have a nice day!");
	}
	
	/*
	 * Method to read and store assignment scores in the respective row of assignment array
	 * @param s Assignment selection
	 * @param s Assignment names
	 * @param n Assignment score array
	 * @param k Scanner class, keyboard object
	 */
	public static void readScores(int s, String[] a, int[][] n, Scanner k)
	{
		int max;
		System.out.print("How many " + a[s - 1] + " scores? ");
		max = k.nextInt();
		
		n[s -1] = new int[max];
		
		for (int i = 0; i < max; i++)
		{
			System.out.print("Enter your score for " + a[s - 1] + " " + (i + 1) + ": "); 
			n[s - 1][i] = k.nextInt();
		}
	}
}
