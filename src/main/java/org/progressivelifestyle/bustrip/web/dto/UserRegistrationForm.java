package org.progressivelifestyle.bustrip.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserRegistrationForm {
	private MultipartFile idBack;
	private MultipartFile idFront;
	private MultipartFile photo;
	
	protected String emailId;
	protected String password;
	protected String mobileNumber;
	protected String birthDate;
	protected String fullName;
	protected String emailVerified;
	protected String phoneVerified;
	
	protected String facebookId;
	protected String tiltUserId;
	protected String tiltCardId;
	
	public UserRegistrationForm(String emailId, String password, String mobileNumber, String birthDate, String fullName, String emailVerified, String phoneVerified, String facebookId, String tiltUserId, String tiltCardId) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.birthDate = birthDate;
		this.fullName = fullName;
		this.emailVerified = emailVerified;
		this.phoneVerified = phoneVerified;
		
		this.facebookId = facebookId;
		this.tiltUserId = tiltUserId;
		this.tiltCardId = tiltCardId;
	}
	public UserRegistrationForm() {}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}
	public String getPhoneVerified() {
		return phoneVerified;
	}
	public void setPhoneVerified(String phoneVerified) {
		this.phoneVerified = phoneVerified;
	}
	@Override
	public String toString() {
		return "UserRegistrationForm [idBack=" + (idBack!=null?idBack.getOriginalFilename():"") + ", emailId=" + emailId + ", password=" + password + ", mobileNumber=" + mobileNumber + ", birthDate=" + birthDate + ", fullName=" + fullName + ", emailVerified=" + emailVerified + ", phoneVerified=" + phoneVerified + "]";
	}
	public MultipartFile getIdBack() {
		return idBack;
	}
	public void setIdBack(MultipartFile idBack) {
		this.idBack = idBack;
	}
	public MultipartFile getIdFront() {
		return idFront;
	}
	public void setIdFront(MultipartFile idFront) {
		this.idFront = idFront;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
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
