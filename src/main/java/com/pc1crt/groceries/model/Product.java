/**
 * 
 */
package com.pc1crt.groceries.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import net.bytebuddy.dynamic.loading.PackageDefinitionStrategy.ManifestReading.SealBaseLocator;

/**
 * @author paul_
 *
 */
@Entity
@Embeddable
public class Product implements Serializable{
	
	@Id
	@GeneratedValue
	private Integer productId;
	@NotBlank(message = "Item name can not be left blank")
	private String name;
	@NotBlank(message = "sku must not be left blank")
	private String sku;
	
	private Double price;
	@NotBlank(message ="weight must not be left blank")
	private String weight;
	@NotBlank(message = "description must not be blank")
	private String description;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateCreated;
	private int stockQuantity;
	private byte[] image;
	@Embedded
	@ManyToMany
	private List<Options> options;
	@ManyToOne
	@Embedded
	private Categories category;
	
	public Product() {}

	public Product(String name, String sku, Double price, String weight, String description, int stockQuantity,
			byte[] image, List<Options> options, Categories category) {
		this.name = name;
		this.sku = sku;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.dateCreated = dateCreated;
		this.stockQuantity = stockQuantity;
		this.image = image;
		this.options = options;
		this.category = category;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setproductId(Integer productId) {
		this.productId = productId;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDate dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", sku=" + sku + ", price=" + price + ", weight=" + weight
				+ ", description=" + description + ", dateCreated=" + dateCreated + ", stockQuantity=" + stockQuantity
				+ ", image=" + Arrays.toString(image) + ", options=" + options + ", category=" + category + "]";
	}
	

}
