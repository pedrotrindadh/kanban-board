package model.dao;

import db.DB;
import model.dao.impl.CardDaoJDBC;

public class DaoFactory {
	public static CardDao createdCardDao() {
		return new CardDaoJDBC(DB.getConnection());
	}
}
