package com.pc1crt.groceries.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address  {
	
	private int houseNumber;
	private String streetName;
	private String town;
	private String city;
	private String postCode;
	
	
	public Address() {}
	
	public Address(int houseNumber, String streetName, String town, String city, String postCode) {
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.town = town;
		this.city = city;
		this.postCode = postCode;
	}

	/**
	 * @return the houseNumber
	 */
	public int getHouseNumber() {
		return houseNumber;
	}

	/**
	 * @param houseNumber the houseNumber to set
	 */
	public void setHouseNumber(int houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @param town the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("").append(houseNumber).append(", ").append(streetName)
				.append(", ").append(town).append(", ").append(city).append(", ").append(postCode)
				.append(".");
		return builder.toString();
	}

	
	
	

}
