package Application;

import static model.entities.enums.CardStatus.TO_DO;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.CardDao;
import model.dao.DaoFactory;
import model.entities.Card;

public class Program {
	
	public static final Scanner sc = new Scanner(System.in);
	
	public static final CardDao cardDao = DaoFactory.createdCardDao();
	
	public static void main(String[] args) {
		
		while(true) {
			System.out.println("Select one of the following options:");
			System.out.println("1 - Show the board");
			System.out.println("2 - Filter a card");
			System.out.println("3 - Create a card");
			System.out.println("4 - Update card title and topic");
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
					updateTitleTopic();
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
		System.out.println("");
		System.out.println("Current board:");
		System.out.println("-----------------------------------------------");
		List<Card> list = cardDao.findAll();
		for (Card card : list) {
			System.out.printf("Id: %d | Title: %s | Status: %s %n"
					+ "Topic: %s %n"
					+ "Created: %s%n", card.getId(), card.getTitle(), card.getCardStatus(), card.getTopic(), card.getCreatedDate());
			System.out.println("-----------------------------------------------");
		}
		System.out.println("");
	}

	private static void filterCard() {
		System.out.println("");
		System.out.print("Choose a card to filter by Id: ");
		int id = sc.nextInt();
		Card card = cardDao.findById(id);
		System.out.println("-----------------------------------------------");
		System.out.printf("Id: %d | Title: %s | Status: %s %n"
				+ "Topic: %s %n"
				+ "Created: %s%n", card.getId(), card.getTitle(), card.getCardStatus(), card.getTopic(), card.getCreatedDate());
		System.out.println("-----------------------------------------------");
		System.out.println("");
	}

	private static void createCard() {
		System.out.println("");
		sc.nextLine(); // Clear the buffer
		System.out.printf("Enter a new title: ");
		String title = sc.nextLine();
		System.out.print("Enter a new topic: ");
		String topic = sc.nextLine();
		Card card = new Card(null,title, topic, new Date(), TO_DO);
		cardDao.insert(card);
		System.out.println("Card created!! Id: " + card.getId());
		System.out.println("");
	}

	private static void updateTitleTopic() {
		System.out.println("");
		System.out.printf("Which card do you want to update?");
		int id = sc.nextInt();
		sc.nextLine(); // Clear the buffer
		System.out.printf("Enter a new title: ");
		String title = sc.nextLine();
		System.out.print("Enter a new topic: ");
		String topic = sc.nextLine();
		Card card = new Card(id,title, topic, null, null);
		cardDao.update(card);
		System.out.println("Card updated!!");
	}

	private static void moveStatus() {
		// TODO Auto-generated method stub
		
	}

	private static void removeCard() {
		// TODO Auto-generated method stub
		
	}
}
