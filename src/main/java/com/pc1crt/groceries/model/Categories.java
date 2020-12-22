/**
 * 
 */
package com.pc1crt.groceries.model;

import java.awt.font.ImageGraphicAttribute;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

import javax.persistence.Embeddable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author paul_
 *
 */
@Entity
@Embeddable
public class Categories implements Serializable{
	@Id
	@GeneratedValue
	private Integer categoriesId;
	@NotBlank(message = "Name can not be left blank")
	private String name;
	@NotBlank(message = "Description can not be left blank")
	private String description;
	private byte[] thumbnail;

	public Categories() {}

	public Categories(String name, String description, byte[] thumbnail) {
		this.name = name;
		this.description = description;
		this.thumbnail = thumbnail;
	}

	public Integer getCategoriesId() {
		return categoriesId;
	}

	public void getCategoriesId(Integer categoryid) {
		this.categoriesId = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desctiption) {
		this.description = desctiption;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] bytes) {
		this.thumbnail = bytes;
	}

	@Override
	public String toString() {
		return "Categories [categoriesId=" + categoriesId + ", name=" + name + ", description=" + description + ", thumbnail="
				+ Arrays.toString(thumbnail) + "]";
	}

	
	
}
