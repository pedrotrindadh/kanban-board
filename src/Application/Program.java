package Application;

import static model.entities.enums.CardStatus.*;

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
			System.out.println("5 - Update card status");
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
					updateStatus();
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
		System.out.print("Enter a new title: ");
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
		System.out.printf("Which card by id do you want to update the title and topic?");
		int id = sc.nextInt();
		sc.nextLine(); // Clear the buffer
		System.out.print("Enter a new title: ");
		String title = sc.nextLine();
		System.out.print("Enter a new topic: ");
		String topic = sc.nextLine();
		Card card = new Card(id,title, topic, null, null);
		cardDao.updateTitleTopic(card);
		System.out.println("Card updated!!");
	}

	private static void updateStatus() {
		System.out.println("");
		System.out.print("Which card by id do you want to update the status?");
		int id = sc.nextInt();
		sc.nextLine(); // Clear the buffer
		System.out.println("Choose status: ");
		System.out.println("1 - To do");
		System.out.println("2 - Processing");
		System.out.println("3 - Done");
		System.out.println("4 - Approved");
		int status = sc.nextInt();
		if (status == 1) {
			Card card = new Card(id, null, null, null, TO_DO);
			cardDao.updateStatus(card);
		}
		else if (status == 2) {
			Card card = new Card(id, null, null, null, PROCESSING);
			cardDao.updateStatus(card);
		}
		else if (status == 3) {
			Card card = new Card(id, null, null, null, DONE);
			cardDao.updateStatus(card);
		}
		else if (status == 4) {
			Card card = new Card(id, null, null, null, APPROVED);
			cardDao.updateStatus(card);
		}
		else {
			System.out.println("Choose a number between 1 and 4");
		}
		System.out.println("Card updated!!");
		System.out.println("");
	}

	private static void removeCard() {
		// TODO Auto-generated method stub
		
	}
}
