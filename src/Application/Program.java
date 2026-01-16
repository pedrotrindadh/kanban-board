package Application;

import static model.entities.enums.CardStatus.*;

import java.util.Date;

import model.entities.Card;

public class Program {
	public static void main(String[] args) {
		
		Card card = new Card(1, "Workout", "workout armys", new Date(),PROCESSING);
		System.out.println(card);
	}
}
