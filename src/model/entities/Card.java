package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import model.entities.enums.CardStatus;

public class Card implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	Integer id;
	String title;
	String topic;
	Date createdDate;
	
	CardStatus cardStatus;
	
	public Card() {
	}
	
	public Card(Integer id, String title, String topic, Date createdDate ,CardStatus cardStatus) {
		this.id = id;
		this.title = title;
		this.topic = topic;
		this.createdDate = createdDate;
		this.cardStatus = cardStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTile(String title) {
		this.title = title;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public CardStatus getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(CardStatus cardStatus) {
		this.cardStatus = cardStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", title=" + title + ", topic=" + topic + ", createdDate=" + createdDate
				+ ", cardStatus=" + cardStatus + "]";
	}
}
