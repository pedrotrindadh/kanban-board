package Application;

import static model.entities.enums.CardStatus.*;

import java.util.Date;
import java.util.List;

import model.dao.CardDao;
import model.dao.DaoFactory;
import model.entities.Card;

public class Program {
	public static void main(String[] args) {
		
		CardDao cardDao = DaoFactory.createdCardDao();
		
		System.out.println("========FIRST TEST: findAll CardDao========");
		List<Card> list = cardDao.findAll();
		for (Card obj:list) {
			System.out.println(obj);
		}
		
		System.out.println("========SECOND TEST: findById CardDao========");
		Card id = cardDao.findById(1);
		System.out.println(id);
		
		System.out.println("========THIRD TEST: create CardDao========");
		Card newCard = new Card(null, "BugFix", "Fix feature of NewBank", new Date(), TO_DO);
		cardDao.create(newCard);
		System.out.println("Created a new card, Id: " + newCard.getId());
		
		System.out.println("========FOURTH TEST: update CardDao========");
		Card updateCard = new Card(7, "Meet", "Meet with all squad", null, null);
		cardDao.update(updateCard);
		System.out.println("Card Updated!");
		
		System.out.println("========FIFTH TEST: move CardDao========");
		Card moveCard = new Card(3, null, null, null, APPROVED);
		cardDao.move(moveCard);;
		System.out.println("Card moved!");
		
		System.out.println("========SIXTH TEST: delete CardDao========");
		cardDao.delete(3);
		System.out.println("Card deleted!");
	}
}
