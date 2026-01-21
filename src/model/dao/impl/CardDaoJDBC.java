package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO card "
					+"(Title, Topic, CreatedDate, CardStatus) "
					+ "VALUES "
					+"(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, card.getTitle());
			st.setString(2, card.getTopic());
			st.setDate(3, new java.sql.Date(card.getCreatedDate().getTime()));
			st.setString(4, card.getCardStatus().name());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					card.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
		
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
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("SELECT * "
					+ "FROM card WHERE Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next()) {
				Card card = instantiateCard(rs);
				return card;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
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
