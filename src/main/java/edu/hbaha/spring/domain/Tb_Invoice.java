package edu.hbaha.spring.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="tb_Invoice")
public class Tb_Invoice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Invoice_ID")
	private int invoiceID;

	@Column(name="CreateBy")
	private int createBy;

	@Column(name="CreateDate")
	private Timestamp createDate;

	@Column(name="DeleteBy")
	private int deleteBy;

	@Column(name="DeleteDate")
	private Timestamp deleteDate;

	@Column(name="Status")
	private boolean status;

	@Column(name="Supplier_ID")
	private int supplierID;

	@Column(name="UpdateBy")
	private int updateBy;

	@Column(name="UpdateDate")
	private Timestamp updateDate;
}