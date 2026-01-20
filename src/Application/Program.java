package Application;

import java.util.List;

import model.dao.CardDao;
import model.dao.DaoFactory;
import model.entities.Card;

public class Program {
	public static void main(String[] args) {
		
		System.out.println("========FIRST TEST: findAll cardDao========");
		
		CardDao cardDao = DaoFactory.createdCardDao();
		
		List<Card> list = cardDao.findAll();
		
		for (Card obj:list) {
			System.out.println(obj);
		}
	}
}
