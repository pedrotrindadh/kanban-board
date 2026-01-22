package Application;

import java.util.Scanner;

public class Program {
	
	public static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Select one of the following options:");
			System.out.println("1 - Show the board");
			System.out.println("2 - Filter a card");
			System.out.println("3 - Create a card");
			System.out.println("4 - Edit the title and topic of card");
			System.out.println("5 - Move the status of card");
			System.out.println("6 - Remove a card");
			System.out.println("7 - Exit");
			
			int option = sc.nextInt();
			
			switch(option) {
				case 1:
					showBoard();
					break;
				case 2:
					filterCard();
					break;
				case 3:
					createCard();
					break;
				case 4:
					editTitleTopic();
					break;
				case 5:
					moveStatus();
					break;
				case 6:
					removeCard();
					break;
				case 7:
					System.exit(0);
					break;
				default:
					System.out.println("Option invalid");
					break;
			}
		}
	}

	private static void showBoard() {
		// TODO Auto-generated method stub
		
	}

	private static void filterCard() {
		// TODO Auto-generated method stub
		
	}

	private static void createCard() {
		// TODO Auto-generated method stub
		
	}

	private static void editTitleTopic() {
		// TODO Auto-generated method stub
		
	}

	private static void moveStatus() {
		// TODO Auto-generated method stub
		
	}

	private static void removeCard() {
		// TODO Auto-generated method stub
		
	}
}
