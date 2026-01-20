package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.CardDao;
import model.entities.Card;
import model.entities.enums.CardStatus;

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
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * "
					+ "FROM card");
			
			rs = st.executeQuery();
			
			List<Card> list = new ArrayList<>();
			
			while (rs.next()) {
				Card obj = instantiateCard(rs);
				list.add(obj);
			}
			
			
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Card instantiateCard(ResultSet rs) throws SQLException {
		Card card = new Card();
		card.setId(rs.getInt("Id"));
		card.setTitle(rs.getString("Title"));
		card.setTopic(rs.getString("Topic"));
		card.setCreatedDate(rs.getDate("createdDate"));
		card.setCardStatus(CardStatus.valueOf(rs.getString("CardStatus").toUpperCase()));
		return card;
	}

}
