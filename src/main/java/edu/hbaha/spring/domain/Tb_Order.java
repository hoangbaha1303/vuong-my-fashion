package edu.hbaha.spring.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Orders")

public class Tb_Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Order_ID")
	private Integer orderID;

	@Column(name="Customer_ID")
	private Integer customerID;

	@Column(name="DeliveryDate")
	private Date deliveryDate;

	@Column(name="ShippingAddress", columnDefinition = "nvarchar(500)")
	private String shippingAddress;

	@Column(name="OrderDate")
	private Date orderDate;

	@Column(name="Status")
	private int status;
}