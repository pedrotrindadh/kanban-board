package Application;

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
	}
}
