//FA2022_16WeeksGradingApplication_Caddell.java

import java.util.Scanner;
import java.io.*;

public class FA2022_16WeeksGradingApplication_Caddell 
{
	final static int ARRAY_SIZE = 7;
	public static void main(String[] args) throws IOException
	{
		
		String[] assignmentNames = {"Quiz", "Homework", "Lab", "Project", "Teamwork", "Topic", "Test"};			//Assignment categories
		int[] assignmentSizes = new int[ARRAY_SIZE];															//How many assignments within each assignment category
		float[] maxScores = new float[ARRAY_SIZE];
		
		int selection;																							//Menu selection
		String input;
		char confirm;
		boolean debug = true;
				//debug = false;
		
		Scanner keyboard = new Scanner(System.in);
		FA2022_Student_Caddell student;
				
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
			
			while(selection < 0 || selection > 7)		//Input validation
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
				//User, confirm input is correct
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
				
				while(confirm != 'y' && confirm != 'n')		//Input validation
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
				case 0:		//Exit
					break;
				case 1:  	//Grade one student		
					//Read student information from keyboard
					student = studentGrades(keyboard, assignmentSizes, assignmentNames);
					
					//Display student information to screen
					System.out.println("\n" + student);
					
					//Write student information to file
					student.toFile();
					
					break;
				case 2:		//Display grade of one student from file
					//Read input from keyboard
					student = studentFile(keyboard, assignmentSizes, assignmentNames);
					
					//Display student information to screen
					System.out.println("\n" + student);
					
					break;
				case 3:		//Display grades of one class
					break;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
		}while(selection != 0);
		
		System.out.println("Thank you! Have a nice day!");
	}

	/** Method to read student information and create object
	 * 
	 * @param keyboard Scanner class object to read keyboard input.
	 * @param assignmentSizes Number of assignments per assignment category
	 * @param assignmentNames Assignment category names
	 * @return Instance of FA2022_Student_Caddell class
	 */
	public static FA2022_Student_Caddell studentGrades(Scanner keyboard, int[] assignmentSizes, String[] assignmentNames)
	{
		String courseName,
			   studentID,
		       studentName;
		float policyQuiz;
		float[][] studentScores = new float[ARRAY_SIZE][];
		String[] studentScoresString = new String[ARRAY_SIZE];
				
		System.out.println("Enter the following information for student: ");
		keyboard.nextLine();
		
		System.out.print("  Course name: ");
		courseName = keyboard.nextLine();
		
		System.out.print("  Student ID: ");
		studentID = keyboard.nextLine();
		
		System.out.print("  Student Name: ");
		studentName = keyboard.nextLine();
		
		System.out.println("Enter the following grades: ");
		
		System.out.print("  Policy Quiz: ");
		policyQuiz = keyboard.nextInt();
		
		for (int row = 0; row < ARRAY_SIZE; row++)
		{
			studentScores[row] = new float[assignmentSizes[row]];

			for (int col = 0; col < assignmentSizes[row]; col++)
			{
				System.out.print("  " + assignmentNames[row] + " " + (col + 1) + ": ");
				studentScores[row][col] = keyboard.nextInt();
			}
		}
		
		for (int row = 0; row < ARRAY_SIZE; row++)
		{
			studentScoresString[row] = "";
			for (int col = 0; col < studentScores[row].length; col++)
			{
				studentScoresString[row] += ((col > 0 ? ", " : "") + studentScores[row][col]);
			}
		}
		
		FA2022_Student_Caddell student = new FA2022_Student_Caddell(courseName, studentID, studentName, policyQuiz, assignmentNames, studentScores, studentScoresString);
		
		return student;
	}

	public static FA2022_Student_Caddell studentFile(Scanner keyboard, int[] assignmentSizes, String[] assignmentNames) throws IOException
	{
		String courseName,
		       studentID = "e1234567",
	           studentName;
		float policyQuiz;
		float[][] studentScores = new float[ARRAY_SIZE][];
		String[] studentScoresString = new String[ARRAY_SIZE];
		String studentFiles;
		String[] testing = new String[50];
		boolean found = false;
		
		//System.out.println("Enter the student ID number: ");
		//studentID = keyboard.nextLine();
		
		File file = new File("student-grades.txt");
		
		if(!file.exists())
		{
			System.out.println("File does not exist.  Terminating program.");
			System.exit(0);
		}
		
		Scanner inputFile = new Scanner(file);
		while(inputFile.hasNext() || !found)
		{	
			studentFiles = inputFile.nextLine();	
			testing = studentFiles.split(", ");
			
			if (testing[1].equals(studentID))    //I think we can binary search this...
			{
				found = true;
			}
		}
		
		//I think I can make this a method...
		courseName = testing[0];
		studentID = testing[1];
		studentName = testing[2];
		policyQuiz = Float.parseFloat(testing[5]);
		
		for (int row = 0; row < ARRAY_SIZE; row++)
		{
			studentScores[row] = new float[assignmentSizes[row]];
			
			for (int col = 0; col < assignmentSizes[row]; col++)
			{
				studentScores[row][col] = Float.parseFloat(testing[6 + row]);
			}
		}
		
		for (int row = 0; row < ARRAY_SIZE; row++) //Definitely make this a method!!!
		{
			studentScoresString[row] = "";
			for (int col = 0; col < studentScores[row].length; col++)
			{
				studentScoresString[row] += ((col > 0 ? ", " : "") + studentScores[row][col]);
			}
		}
		
		FA2022_Student_Caddell studentFile = new FA2022_Student_Caddell(courseName, studentID, studentName, policyQuiz, assignmentNames, studentScores, studentScoresString);
		
		inputFile.close();
		return studentFile;
	}
}
