package com.pc1crt.groceries.model;

import javax.persistence.Embeddable;

@Embeddable
public class CardDetails {
	private String cardNumber;
	private String expirationMonth;
	private String expirationYear;
	private String ccv;
	private String nameOnCard;
	
	public CardDetails() {
		
	}

	/**
	 * @param cardNumber
	 * @param expirationMonth
	 * @param expirationYear
	 * @param ccv
	 * @param nameOnCard
	 */
	public CardDetails(String cardNumber, String expirationMonth, String expirationYear, String ccv, String nameOnCard) {
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.ccv = ccv;
		this.nameOnCard = nameOnCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getCcv() {
		return ccv;
	}

	public void setCcv(String ccv) {
		this.ccv = ccv;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardDetails [cardNumber=").append(cardNumber).append(", expirationMonth=")
				.append(expirationMonth).append(", expirationYear=").append(expirationYear).append(", ccv=").append(ccv)
				.append(", nameOnCard=").append(nameOnCard).append("]");
		return builder.toString();
	}
	
	
}
