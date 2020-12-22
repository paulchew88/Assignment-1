package com.pc1crt.groceries.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class Orders {
	@Id
	@GeneratedValue
	private Integer ordersId;
	@ManyToOne
	@Embedded
	private Customer customer;
	private Address shippingAddress;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name ="order_date")
	private LocalDate oderDate;
	private OrderStatus orderStatus;
	@Embedded
	@ManyToMany
	private List<Product> products;
	
	public Orders() {}
	public Orders(Customer customer, Address shippingAddress,
		 LocalDate oderDate, OrderStatus orderStatus) {
		this.customer = customer;
		this.shippingAddress = shippingAddress;
		this.oderDate = oderDate;
		this.orderStatus = orderStatus;
	}


	public Integer getOrdersId() {
		return ordersId;
	}


	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public Address getShippingAddress() {
		return shippingAddress;
	}


	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public LocalDate getOderDate() {
		return oderDate;
	}


	public void setOderDate(LocalDate oderDate) {
		this.oderDate = oderDate;
	}


	public OrderStatus getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> cart) {
		this.products = cart;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orders [ordersId=").append(ordersId).append(", customer=").append(customer)
				.append(", shippingAddress=").append(shippingAddress).append(", oderDate=").append(oderDate)
				.append(", orderStatus=").append(orderStatus).append(", products=").append(products).append("]");
		return builder.toString();
	}
	
	
	
	

}
