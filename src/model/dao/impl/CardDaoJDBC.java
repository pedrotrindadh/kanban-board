package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.CardDao;
import model.entities.Card;

public class CardDaoJDBC implements CardDao {
	
	private Connection conn;
	
	public CardDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void create(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Card findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Card> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
