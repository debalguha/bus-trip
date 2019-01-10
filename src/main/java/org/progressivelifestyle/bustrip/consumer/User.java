package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="ID")
public class User extends Consumer {
	private int verificationCode;
	private String facebookId;
	private String tiltUserId;
	private String tiltCardId;
	public User() {}
	public User(String emailId, String password, String mobileNumber, Date birthDate, String fullName, boolean emailVerified, boolean phoneVerified, byte[] idFront, byte[] idBack, byte[] photo, String creditCardJSON, String facebookId, String tiltUserId, String tiltCardId) {
		super(emailId, password, mobileNumber, birthDate, fullName, emailVerified, phoneVerified, idFront, idBack, photo, creditCardJSON);
		this.role = Role.USER;
		this.facebookId = facebookId;
		this.tiltUserId = tiltUserId;
		this.tiltCardId = tiltCardId;
	}
	
	public int getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getFacebookId() {
		return facebookId;
	}
	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}
	public String getTiltUserId() {
		return tiltUserId;
	}
	public void setTiltUserId(String tiltUserId) {
		this.tiltUserId = tiltUserId;
	}
	public String getTiltCardId() {
		return tiltCardId;
	}
	public void setTiltCardId(String tiltCardId) {
		this.tiltCardId = tiltCardId;
	}
}
