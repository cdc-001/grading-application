import java.util.Scanner;

public class Validate 
{	
	public static void integerInput(Scanner keyboard)
	{
		while(!keyboard.hasNextInt())
		{
			System.out.print("Invalid selection.  Please, try again: ");
			keyboard.nextLine();
		}
	
	}
	
	public static int menuSelect(Scanner keyboard, int min, int max)
	{
		int menuSelect;
		
		do
		{
			integerInput(keyboard);
			menuSelect = keyboard.nextInt();
			
			if (menuSelect < min || menuSelect > max)
			{
				System.out.print("Invalid selection.  Please, try again: ");
				keyboard.nextLine();	
			}
		}while(menuSelect < min || menuSelect > max);	
	
		return menuSelect;
	}
}
