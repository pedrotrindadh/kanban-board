package model.dao;

import java.util.List;

import model.entities.Card;

public interface CardDao {
	
	void insert(Card card);
	void updateTitleTopic(Card card);
	void updateStatus(Card card);
	void delete(Integer id);
	Card findById(Integer id);
	List<Card> findAll();
}
