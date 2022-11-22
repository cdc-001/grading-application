//FA2022_16WeeksGradingApplication_Caddell.java

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FA2022_16WeeksGradingApplication_Caddell 
{
	/** This program calculates grades for students and displays student information reports */
	
 	public static void main(String[] args) throws IOException
	{
		final String[] ASSIGNMENT_NAMES = {"Quiz", "Homework", "Lab", "Project", "Teamwork", "Topic", "Test"};		//Assignment categories, per program requirements.
		final int[] MAX_ASSIGNMENT_SIZE = { 14, 10, 7, 1, 1, 1, 3 }; 												//Maximum number of assignments, per program requirements.
		final int SENTINEL = 0;																						//Exit menu loops.

		int[] assignmentSizes = new int[ASSIGNMENT_NAMES.length];											//Number of scores to enter for each assignment category, per user.
		float[] maxScores = new float[ASSIGNMENT_NAMES.length];												//Total score for each assignment category, per user.
		
		Scanner keyboard = new Scanner(System.in);																			
		int selection;																						//Menu selection.
		
		FA2022_Student_Caddell student;																		//Reference to instance of student class.
		ArrayList<FA2022_Student_Caddell> allStudents = new ArrayList<>();									//ArrayList containing all instances of student class.
				
		do
		{
			//Display menu to screen
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "LIST OF ASSIGNMENTS  – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n");
			for (int i = 0; i < ASSIGNMENT_NAMES.length; i++)
			{
				System.out.println((i + 1) + "." + ASSIGNMENT_NAMES[i]);						
			}
			System.out.println(SENTINEL + ".Exit\n" );
			
			//Read input from keyboard - menu selection
			System.out.print("Make a selection from the above menu: ");
			selection = Validate.menuSelect(keyboard, SENTINEL, ASSIGNMENT_NAMES.length);					//Validate selection. Arguments include minimum menu value and maximum menu value, respectively.
			
			if (selection > 0)
			{
				//Read input from keyboard - assignment information
				assignmentInfo(keyboard, ASSIGNMENT_NAMES, selection, assignmentSizes, maxScores, MAX_ASSIGNMENT_SIZE);
			}
			else
			{
				//Allow user to review their input
				selection = userConfirm(keyboard, ASSIGNMENT_NAMES, selection, assignmentSizes, maxScores);
			}
		}while(selection != SENTINEL);
				
		do
		{
			//Display menu
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "TASK OF GRADING – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n"
							 + "1.Grading One Student\n"
							 + "2.Printing the Grade of One Student from input file\n"
							 + "3.Printing the Grades of Class\n"
							 + "0.Exit\n");
			
			//Read input from keyboard - menu selection
			System.out.print("Make a selection from the above menu: ");
			selection = Validate.isPositiveInteger(keyboard);											//Validate selection.
			
			switch (selection)
			{
				case 0:		//Exit menu
					break;
				case 1:  	//Grade one student		
					//Read student information from keyboard
					student = studentGradesFromKeyboard(keyboard, assignmentSizes, ASSIGNMENT_NAMES, maxScores, MAX_ASSIGNMENT_SIZE);
					
					//Display student information to screen
					System.out.println("\n" + student);
					
					//Write student information to file
					student.toFile();
					break;
				case 2:		//Display grade of one student from file
					//Read input from keyboard and file
					student = studentGradesFromFile(keyboard, assignmentSizes, ASSIGNMENT_NAMES, MAX_ASSIGNMENT_SIZE);
					
					//Display student information to screen
					if (student == null)
					{
						System.out.println("\nCould not locate student in file.\n");
					}
					else
					{
						System.out.println(student);

					}					
					break;
				case 3:		//Display grades of entire class
					//Read input from file
					allStudents.addAll(allStudentGradesFromFile(assignmentSizes, ASSIGNMENT_NAMES, MAX_ASSIGNMENT_SIZE));
										
					//Display student information to screen
					System.out.println("\nFA2022_16WeeksGradingApplication_Caddell.java\n"
									 + "LIST OF STUDENTS' GRADES - CORY CADDELL\n"
									 + "---------------------------------------------");
					for (FA2022_Student_Caddell display : allStudents)
					{
						System.out.println(display.shortOutput()
									 + "---------------------------------------------");
					}
					System.out.println();
					break;
				default:
					System.out.println("Invalid entry.  Please, try again.");
			}
		}while(selection != SENTINEL);
		
		System.out.println("\nThank you! Have a nice day!");
	}
	
	/** Method to read assignment info and assign to array.	 */
	public static void assignmentInfo(Scanner keyboard, String[] ASSIGNMENT_NAMES, int selection, int[] assignmentSizes, float[] maxScores, int[] MAX_ASSIGNMENT_SIZE)
	{		
		System.out.print("\nHow many " + ASSIGNMENT_NAMES[selection - 1] + " scores (not to exceed " + MAX_ASSIGNMENT_SIZE[selection -1] + ")? ");
		assignmentSizes[selection - 1] = Validate.isPositiveInteger(keyboard);
		
		while (assignmentSizes[selection - 1] > MAX_ASSIGNMENT_SIZE[selection - 1])		// Ensure number of assignments entered by user is not larger than max.
		{
			System.out.print("Amount of " + ASSIGNMENT_NAMES[selection -1] + " scores cannot exceed " + MAX_ASSIGNMENT_SIZE[selection -1] + ". Please, try again: ");
			assignmentSizes[selection - 1] = Validate.isPositiveInteger(keyboard);
		}
		
		System.out.print("Enter maximum score for each " + ASSIGNMENT_NAMES[selection - 1] + " assignment: "); 
		maxScores[selection - 1] = Validate.isPositiveFloat(keyboard) * assignmentSizes[selection - 1];
		
		System.out.println();
		keyboard.nextLine();
	}
	
	/** Method to allow user to review their input.
	 * @return Yes returns SENTINEL value, 0.  No returns -1 to revert user back to main menu.
	 */
	public static int userConfirm(Scanner keyboard, String[] ASSIGNMENT_NAMES, int selection, int[] assignmentSizes, float[] maxScores)
	{
		String input = "";												//Used to convert from string to primitive data type.
		float totalMaxScore;											//Cumulative max possible score on all assignments
		
		System.out.println("\nConfirm the following is correct: ");
		
		totalMaxScore = 0.0f;
		for (int i = 0; i < ASSIGNMENT_NAMES.length; i++)
		{
			System.out.println("  " + String.format("%-10s%-25s%-25s", ASSIGNMENT_NAMES[i] + ": ", "Total assignments = " 
							 + assignmentSizes[i] + "; ", "Maximum score = " + maxScores[i]));
			totalMaxScore += maxScores[i];
		}
		System.out.println("  ----------------------------------------------------------\n"
				+ "  Total: " + totalMaxScore);
		
		System.out.print("Enter 'y' to continue or 'n' to return to main menu: ");
		keyboard.nextLine();											
		input = keyboard.nextLine();
		
		if (Validate.yesOrNo(keyboard, input) == 'y') 					//Validate whether user entered y or n.
		{
			selection = 0;												//Proceed to next menu
		}
		else
		{
			selection = -1;												//Return to main menu
		}
		
		System.out.println();
		
		return selection;
	}

	/** Method to read student information from keyboard and create object.	 */
	public static FA2022_Student_Caddell studentGradesFromKeyboard(Scanner keyboard, int[] assignmentSizes, String[] ASSIGNMENT_NAMES, float[] maxScores, int[] MAX_ASSIGNMENT_SIZE)
	{
		String courseName,															//Name of course
			   firstName,															//Student first name
			   lastName,															//Student last name
			   studentID;															//Student ID number
		float policyQuiz;															//Extra credit assignment
		float[][] studentScores = new float[ASSIGNMENT_NAMES.length][];				//Ragged array of student scores per assignment category
		String[] studentScoresString = new String[ASSIGNMENT_NAMES.length];			//String version of student score array for display purposes
				
		System.out.println("\nEnter the following information for the student: ");
		keyboard.nextLine();														
		
		System.out.print("  Course name: ");
		courseName = keyboard.nextLine();
		
		System.out.print("  Student ID: ");
		studentID = keyboard.nextLine();
		
		System.out.print("  Student first name: ");
		firstName = keyboard.nextLine();
		
		System.out.print("  Student last name: ");
		lastName = keyboard.nextLine();
		
		System.out.println("Enter the following grades: ");
		
		System.out.print("  Policy Quiz: ");
		policyQuiz = Validate.isPositiveFloat(keyboard);
		
		for (int row = 0; row < ASSIGNMENT_NAMES.length; row++)
		{
			studentScores[row] = new float[MAX_ASSIGNMENT_SIZE[row]];					//Set ragged array columns

			for (int col = 0; col < assignmentSizes[row]; col++)
			{
				System.out.print("  " + ASSIGNMENT_NAMES[row] + " " + (col + 1) + ": ");
				studentScores[row][col] = Validate.isPositiveFloat(keyboard);
				keyboard.nextLine();
			}
		}
				
		scoresToString(ASSIGNMENT_NAMES, studentScoresString, studentScores);
		
		FA2022_Student_Caddell student = new FA2022_Student_Caddell(courseName, studentID, firstName, lastName, policyQuiz, ASSIGNMENT_NAMES, studentScores, studentScoresString, maxScores);	
				
		return student;
	}
	
	/** Method to read student information from file and create object.	 */
	public static FA2022_Student_Caddell studentGradesFromFile(Scanner keyboard, int[] assignmentSizes, String[] ASSIGNMENT_NAMES, int[] MAX_ASSIGNMENT_SIZE) throws IOException
	{
		String searchForMe,																//Student ID number user chooses to search for
			   inputLine;																//Line of information for found student
		String[] fileLineElements;														//Array to hold each element of the line string separately
		boolean found = false;															//Stop loop if student ID is found in file.
		FA2022_Student_Caddell studentFile = null;
				
		System.out.print("\nEnter the student ID number you wish to search for: ");
		keyboard.nextLine();
		searchForMe = keyboard.nextLine();
		
		File file = new File("student-grades.txt");
		
		if(!file.exists())
		{
			System.out.println("File does not exist.  Terminating program.");
			System.exit(0);													
		}
		
		Scanner inputFile = new Scanner(file);
		while(inputFile.hasNext() && !found)	
		{	
			inputLine = inputFile.nextLine();	
			fileLineElements = new String[inputLine.length()];
			fileLineElements = inputLine.split("[, ]");
									
			if (fileLineElements[1].equals(searchForMe))    							//Student IDs are stored in the second element of the array.
			{
				studentFile = studentScoresFromFile(ASSIGNMENT_NAMES, assignmentSizes, fileLineElements, MAX_ASSIGNMENT_SIZE);
				found = true;
			}
		}
		System.out.println();
		
		inputFile.close();
		return studentFile;
	}
	
	/** Method to read classroom scores from file and create object.	 */
	public static ArrayList<FA2022_Student_Caddell> allStudentGradesFromFile (int[] assignmentSizes, String[] ASSIGNMENT_NAMES, int[] MAX_ASSIGNMENT_SIZE) throws IOException
	{
		String inputLine;																	//Line of information for student
		String[] fileLineElements;															//Array to hold each element of inputLine

		ArrayList<FA2022_Student_Caddell> allStudentFiles = new ArrayList<>();				//Array to hold all student objects
		
		File file = new File("student-grades.txt");
		if(!file.exists())
		{
			System.out.println("File does not exist.  Terminating program.");
			System.exit(0);
		}
		
		Scanner inputFile = new Scanner(file);
		while(inputFile.hasNext())
		{	
			inputLine = inputFile.nextLine();	
			fileLineElements = new String[inputLine.length()];
			fileLineElements = inputLine.split("[, ]");

			allStudentFiles.add(studentScoresFromFile(ASSIGNMENT_NAMES, assignmentSizes, fileLineElements, MAX_ASSIGNMENT_SIZE));
		}
		
		inputFile.close();
		return allStudentFiles;
	}
	
	/** Method to convert student scores from primitive value to string.	 */
	public static void scoresToString(String[] ASSIGNMENT_NAMES, String[] studentScoresString, float[][] studentScores)
	{	
		for (int row = 0; row < ASSIGNMENT_NAMES.length; row++)
		{
			studentScoresString[row] = "";
			for (int col = 0; col < studentScores[row].length; col++)
			{
				if (studentScores[row][col] != 0)
				{
					studentScoresString[row] += (String.format("%.2f ", studentScores[row][col]));
				}
			}
		}
	}
	
	/** Method to read scores from file and assign to array.	 */
	public static FA2022_Student_Caddell studentScoresFromFile(String[] ASSIGNMENT_NAMES, int[] assignmentSizes, String[] fileLineElements, int[] MAX_ASSIGNMENT_SIZE)
	{		
		float[][] studentScores = new float[ASSIGNMENT_NAMES.length][];
		String[] studentScoresString = new String[ASSIGNMENT_NAMES.length];
		float[] maxScorePerFile = new float[ASSIGNMENT_NAMES.length];							//Max score is not saved in file, so need to back into it when creating student objects.
		
		String courseName = fileLineElements[0];
		String studentID = fileLineElements[1];
		String lastName = fileLineElements[2];
		String firstName = fileLineElements[3];
		Float policyQuiz = Float.parseFloat(fileLineElements[6]);
		
		int count = 7;	
		maxScorePerFile[0] = policyQuiz;														//Back into total max score for students saved on file.
		for (int row = 0; row < ASSIGNMENT_NAMES.length; row++)
		{
			studentScores[row] = new float[MAX_ASSIGNMENT_SIZE[row]];							//Set columns for ragged array.
			
			for (int col = 0; col < MAX_ASSIGNMENT_SIZE[row]; col++)
			{
				studentScores[row][col] = Float.parseFloat(fileLineElements[count]);
				maxScorePerFile[0] += studentScores[row][col];									//Back into total max score for students saved on file.
				count++;
			}
		}
		
		maxScorePerFile[0] /= Float.parseFloat(fileLineElements[4]) / 100;						//Back into total max score for students saved on file.
			
		scoresToString(ASSIGNMENT_NAMES, studentScoresString, studentScores);

		FA2022_Student_Caddell studentFile = new FA2022_Student_Caddell(courseName, studentID, firstName, lastName, policyQuiz, ASSIGNMENT_NAMES, studentScores, studentScoresString, maxScorePerFile);
		
		return studentFile;
	}
}
