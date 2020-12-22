package com.pc1crt.groceries.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
@Embeddable
public class Options implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "option_name")
	private String optionName;

	public Options() {
	}

	public Options(String optionName) {
		this.optionName = optionName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	@Override
	public String toString() {
		return "Options [id=" + id + ", optionName=" + optionName + "]";
	}

}
