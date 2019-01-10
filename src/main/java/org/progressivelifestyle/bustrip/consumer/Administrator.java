package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="ID")
public class Administrator extends Consumer {

	public Administrator() {}

	public Administrator(String emailId, String password, String mobileNumber, Date birthDate, String fullName, boolean emailVerified, boolean phoneVerified, byte[] idFront, byte[] idBack, byte[] photo, String creditCardJSON) {
		super(emailId, password, mobileNumber, birthDate, fullName, emailVerified, phoneVerified, idFront, idBack, photo, creditCardJSON);
		this.role = Role.ADMIN;
	}

}
