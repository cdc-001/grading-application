//Validate.java

import java.util.Scanner;

public class Validate 
{	
	/** Method to validate whether an integer was entered by user from keyboard.	 */
	public static void isInteger(Scanner keyboard)
	{
		while(!keyboard.hasNextInt())
		{
			System.out.print("Invalid entry.  Please, try again: ");
			keyboard.nextLine();
		}
	}
	
	/** Method to validate whether an positive float was entered by user from keyboard.	 */
	public static float isPositiveFloat(Scanner keyboard)
	{
		float num = 0.0f;
		
		do
		{
			while(!keyboard.hasNextFloat())
			{
				System.out.print("Invalid entry. Please, try again: ");
				keyboard.nextLine();
			}
			
			num = keyboard.nextFloat();
			if (num < 0)
			{
				System.out.print("Invalid entry. Please, try again:");
				keyboard.nextLine();
			}
		}while(num < 0);
		
		
		return num;
	
	}

	/** Method to validate whether an positive double was entered by user from keyboard.	 */
	public static double isPositiveDouble(Scanner keyboard)
	{
		double num = 0.0;
		
		do
		{
			while(!keyboard.hasNextDouble())
			{
				System.out.print("Invalid entry. Please, try again: ");
				keyboard.nextLine();
			}
			
			num = keyboard.nextDouble();
			if (num < 0)
			{
				System.out.print("Invalid entry. Please, try again:");
				keyboard.nextLine();
			}
		}while(num < 0);
		
		
		return num;
	}
	
	/** Method to validate whether a positive integer was entered by user from keyboard.	 */
	public static int isPositiveInteger(Scanner keyboard)
	{
		int num = 0;
		
		do
		{
			while(!keyboard.hasNextInt())
			{
				System.out.print("Invalid entry. Please, try again: ");
				keyboard.nextLine();
			}
			
			num = keyboard.nextInt();
			if (num < 0)
			{
				System.out.print("Invalid entry. Please, try again:");
				keyboard.nextLine();
			}
		}while(num < 0);
		
		
		return num;
	}
	
	/** Method to validate whether menu choices were selected by user from keyboard.	 */
	public static int menuSelect(Scanner keyboard, int min, int max)
	{
		int menuSelect;
		
		do
		{
			isInteger(keyboard);
			menuSelect = keyboard.nextInt();
			
			if (menuSelect < min || menuSelect > max)
			{
				System.out.print("Invalid entry.  Please, try again: ");
				keyboard.nextLine();	
			}
		}while(menuSelect < min || menuSelect > max);	
	
		return menuSelect;
	}
	
	/** Method to validate yes or no data input	 */
	public static char yesOrNo(Scanner keyboard, String input)
	{
		char yorn = input.toLowerCase().charAt(0);
		
		while(yorn != 'y' && yorn != 'n')		
		{
			System.out.print("Invalid entry. "
							+ "Enter 'y' to proceed or 'n' to return to main menu: ");
			input = keyboard.nextLine();
			yorn = input.toLowerCase().charAt(0);
		}
		
		return yorn;
	}
}
