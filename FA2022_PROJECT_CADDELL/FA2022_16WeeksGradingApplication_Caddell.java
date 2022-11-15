//FA2022_16WeeksGradingApplication_Caddell.java

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class FA2022_16WeeksGradingApplication_Caddell 
{
	public static void main(String[] args) throws IOException
	{
		String[] assignmentNames = {"Quiz", "Homework", "Lab", "Project", "Teamwork", "Topic", "Test"};		//Assignment categories
		int[] assignmentSizes = new int[assignmentNames.length];											//How many assignments within each assignment category
		float[] maxScores = new float[assignmentNames.length];												//Total score for each assignment category
		
		Scanner keyboard = new Scanner(System.in);																			
		final int sentinel = 0;
		int selection;																						//Menu selection
		
		FA2022_Student_Caddell student;																		//Reference to instance of student class
		ArrayList<FA2022_Student_Caddell> allStudents = new ArrayList<>();									//ArrayList containing all instances of student class
				
		do
		{
			//Display menu to screen
			System.out.println("FA2022_16WeeksGradingApplication_Caddell.java\n"
							 + "LIST OF ASSIGNMENTS  – CORY CADDELL\n"
							 + "--------------------------------------------------------------------\n");
			for (int i = 0; i < assignmentNames.length; i++)
			{
				System.out.println((i + 1) + "." + assignmentNames[i]);						
			}
			System.out.println(sentinel + ".Exit\n" );
			
			//Read input from keyboard
			System.out.print("Make a selection from the above menu: ");
			selection = Validate.menuSelect(keyboard, sentinel, assignmentNames.length);					//Validate selection. Arguments include minimum menu value and maximum menu value, respectively.
			
			if (selection > 0)
			{
				//Read input from keyboard
				assignmentInfo(keyboard, assignmentNames, selection, assignmentSizes, maxScores);
			}
			else
			{
				//Allow user to review their input
				selection = userConfirm(keyboard, assignmentNames, selection, assignmentSizes, maxScores);
			}
		}while(selection != sentinel);
				
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
			
			//Read input from keyboard
			System.out.print("Make a selection from the above menu: ");
			selection = Validate.menuSelect(keyboard, sentinel, 3);											//Validate selection. Arguments include minimum menu value and maximum menu value, respectively.
			
			switch (selection)
			{
				case 0:		//Exit
					break;
				case 1:  	//Grade one student		
					//Read student information from keyboard
					student = studentGradesFromKeyboard(keyboard, assignmentSizes, assignmentNames, maxScores);
					
					//Display student information to screen
					System.out.println("\n" + student);
					
					//Write student information to file
					student.toFile();
					break;
				case 2:		//Display grade of one student from file
					//Read input from keyboard and file
					student = studentGradesFromFile(keyboard, assignmentSizes, assignmentNames, maxScores);
					
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
				case 3:		//Display grades of one class
					//Read input from file
					allStudents.addAll(allStudentGradesFromFile(assignmentSizes, assignmentNames, maxScores));
										
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
			}
		}while(selection != sentinel);
		
		System.out.println("\nThank you! Have a nice day!");
	}
	
	/** Method to read assignment info and assign to array.
	 * 
	 * @param keyboard Scanner class object to read keyboard input.
	 * @param assignmentNames Assignment category names.
	 * @param selection Menu selection entered by user.
	 * @param assignmentSizes Number of assignments per assignment category.
	 * @param maxScores Total score for each assignment category
	 */
	public static void assignmentInfo(Scanner keyboard, String[] assignmentNames, int selection, int[] assignmentSizes, float[] maxScores)
	{
		System.out.print("\nHow many " + assignmentNames[selection - 1] + " scores? ");
		assignmentSizes[selection - 1] = keyboard.nextInt();
		
		System.out.print("Enter maximum score for each " + assignmentNames[selection - 1] + " assignment: "); 
		maxScores[selection - 1] = keyboard.nextInt() * assignmentSizes[selection - 1];
		
		System.out.println();
		keyboard.nextLine();
	}
	
	/** Method to allow user to review their input.
	 * 
	 * @param keyboard Scanner class object to read keyboard input.
	 * @param assignmentNames Assignment category names.
	 * @param selection Menu selection entered by user.
	 * @param assignmentSizes Number of assignments per assignment category.
	 * @param maxScores Total score for each assignment category
	 * @return Yes return sentinel value, 0.  No returns -1 to return user to main menu.
	 */
	public static int userConfirm(Scanner keyboard, String[] assignmentNames, int selection, int[] assignmentSizes, float[] maxScores)
	{
		String input = "";												//Used to convert from string to primitive data type.
		float totalMaxScore;											//Cumulative max possible score on all assignments
		
		System.out.println("\nConfirm the following is correct: ");
		
		totalMaxScore = 0.0f;
		for (int i = 0; i < assignmentNames.length; i++)
		{
			System.out.println("  " + String.format("%-10s%-25s%-25s", assignmentNames[i] + ": ", "Total assignments = " 
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

	/** Method to read student information from keyboard and create object.
	 * 
	 * @param keyboard Scanner class object to read keyboard input.
	 * @param assignmentSizes Number of assignments per assignment category.
	 * @param assignmentNames Assignment category names.
	 * @return Instance of FA2022_Student_Caddell class.
	 */
	public static FA2022_Student_Caddell studentGradesFromKeyboard(Scanner keyboard, int[] assignmentSizes, String[] assignmentNames, float[] maxScores)
	{
		String courseName,															
			   studentID,
		       studentName;
		float policyQuiz;
		float[][] studentScores = new float[assignmentNames.length][];
		String[] studentScoresString = new String[assignmentNames.length];
				
		System.out.println("\nEnter the following information for the student: ");
		keyboard.nextLine();														
		
		System.out.print("  Course name: ");
		courseName = keyboard.nextLine();
		
		System.out.print("  Student ID: ");
		studentID = keyboard.nextLine();
		
		System.out.print("  Student Name: ");
		studentName = keyboard.nextLine();
		
		System.out.println("Enter the following grades: ");
		
		System.out.print("  Policy Quiz: ");
		policyQuiz = Validate.isPositiveFloat(keyboard);
		
		for (int row = 0; row < assignmentNames.length; row++)
		{
			studentScores[row] = new float[assignmentSizes[row]];							//Set ragged array columns

			for (int col = 0; col < assignmentSizes[row]; col++)
			{
				System.out.print("  " + assignmentNames[row] + " " + (col + 1) + ": ");
				studentScores[row][col] = Validate.isPositiveFloat(keyboard);
				keyboard.nextLine();
			}
		}
				
		scoresToString(assignmentNames, studentScoresString, studentScores);
		
		FA2022_Student_Caddell student = new FA2022_Student_Caddell(courseName, studentID, studentName, policyQuiz, assignmentNames, studentScores, studentScoresString, maxScores);	
				
		return student;
	}
	
	/** Method to read student information from file and create object
	 * 
	 * @param keyboard Scanner class object to read keyboard input.
	 * @param assignmentSizes Number of assignments per assignment category.
	 * @param assignmentNames Assignment category names.
	 * @return Instance of FA2022_Student_Caddell class.
	 */
	public static FA2022_Student_Caddell studentGradesFromFile(Scanner keyboard, int[] assignmentSizes, String[] assignmentNames, float[] maxScores) throws IOException
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
			fileLineElements = inputLine.split(", ");
									
			if (fileLineElements[1].equals(searchForMe))    							//Student IDs are stored in the second element of the array.
			{
				studentFile = studentScoresFromFile(assignmentNames, assignmentSizes, fileLineElements, maxScores);
				found = true;
			}
		}
		
		inputFile.close();
		return studentFile;
	}
	
	/** Method to read classroom scores from file and create object.
	 * 
	 */
	public static ArrayList<FA2022_Student_Caddell> allStudentGradesFromFile (int[] assignmentSizes, String[] assignmentNames, float[] maxScores) throws IOException
	{
		String inputLine;
		String[] fileLineElements;

		ArrayList<FA2022_Student_Caddell> allStudentFiles = new ArrayList<>();
		
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
			fileLineElements = inputLine.split(", ");

			allStudentFiles.add(studentScoresFromFile(assignmentNames, assignmentSizes, fileLineElements, maxScores));
		}
		
		inputFile.close();
		return allStudentFiles;
	}
	
	
	/** Method to convert student scores from primitive value to string.
	 * 
	 * @param assignmentNames Assignment category names.
	 * @param studentScoresString Object containing student scores via a string
	 * @param studentScores Score per assignment
	 */
	public static void scoresToString(String[] assignmentNames, String[] studentScoresString, float[][] studentScores)
	{	
		for (int row = 0; row < assignmentNames.length; row++)
		{
			studentScoresString[row] = "";
			for (int col = 0; col < studentScores[row].length; col++)
			{
				studentScoresString[row] += ((col > 0 ? ", " : "")
							+ String.format("%.2f", studentScores[row][col]));
			}
		}
	}
	
	/** Method to read scores from file and assign to array.
	 * 
	 * @param assignmentNames
	 * @param studentScores
	 * @param assignmentSizes
	 */
	public static FA2022_Student_Caddell studentScoresFromFile(String[] assignmentNames, int[] assignmentSizes, String[] fileLineElements, float[]maxScores)
	{		
		float[][] studentScores = new float[assignmentNames.length][];
		String[] studentScoresString = new String[assignmentNames.length];
		
		String courseName = fileLineElements[0];
		String studentID = fileLineElements[1];
		String studentName = fileLineElements[2];
		Float policyQuiz = Float.parseFloat(fileLineElements[5]);
		
		int count = 6;	
		for (int row = 0; row < assignmentNames.length; row++)
		{
			studentScores[row] = new float[assignmentSizes[row]];
			
			for (int col = 0; col < assignmentSizes[row]; col++)
			{
				studentScores[row][col] = Float.parseFloat(fileLineElements[count]);
				count++;
			}
		}
			
		scoresToString(assignmentNames, studentScoresString, studentScores);

		FA2022_Student_Caddell studentFile = new FA2022_Student_Caddell(courseName, studentID, studentName, policyQuiz, assignmentNames, studentScores, studentScoresString, maxScores);
		
		return studentFile;
	}
}
