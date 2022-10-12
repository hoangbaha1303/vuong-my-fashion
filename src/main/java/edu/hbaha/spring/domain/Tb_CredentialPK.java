package edu.hbaha.spring.domain;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The primary key class for the tb_Credential database table.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Tb_CredentialPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UserGroup_ID")
	private int userGroupID;

	@Column(name="Role_ID")
	private int roleID;

}