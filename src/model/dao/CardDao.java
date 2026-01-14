package model.dao;

import java.util.List;

import model.entities.Card;

public interface CardDao {
	
	void create(Card card);
	void update(Card card);
	void move(Card card);
	void delete(Integer id);
	Card findById(Integer id);
	List<Card> findAll();
}
