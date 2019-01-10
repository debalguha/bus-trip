package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

import org.progressivelifestyle.bustrip.google.domain.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.JOINED) 
public class Consumer extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false)
	protected String emailId;
	@Column(nullable = false)
	protected String password;
	@Column(nullable = false)
	protected String mobileNumber;
	@Column(nullable = false)
	protected Date birthDate;
	@Column(nullable = false)
	protected String fullName;
	@Column(nullable = false)
	protected boolean emailVerified;
	@Column(nullable = false)
	protected boolean phoneVerified;

	@Lob
	@JsonIgnore
	@Column(nullable = true)
	protected byte[] idFront;
	@Lob
	@JsonIgnore
	@Column(nullable = true)
	protected byte[] idBack;
	@Lob
	@JsonIgnore
	@Column(nullable = true)
	protected byte[] photo;
	
	@JsonIgnore
	@Column(nullable = false)
	private Date creationDate;
	@JsonIgnore
	@Column(nullable = false)
	private Date lastUpdateDate;	

	@JsonIgnore
	@Column(nullable = true)
	protected String creditCardJSON;
	
	protected Role role;

	public Consumer() {
	}

	public Consumer(String emailId, String password, String mobileNumber, Date birthDate, String fullName, boolean emailVerified, boolean phoneVerified, byte[] idFront, byte[] idBack, byte[] photo, String creditCardJSON) {
		super();
		this.emailId = emailId;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.birthDate = birthDate;
		this.fullName = fullName;
		this.emailVerified = emailVerified;
		this.phoneVerified = phoneVerified;
		this.idFront = idFront;
		this.idBack = idBack;
		this.photo = photo;
		this.creditCardJSON = creditCardJSON;
	}

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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public boolean isPhoneVerified() {
		return phoneVerified;
	}

	public void setPhoneVerified(boolean phoneVerified) {
		this.phoneVerified = phoneVerified;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consumer other = (Consumer) obj;
		if (emailId == null) {
			if (other.emailId != null)
				return false;
		} else if (!emailId.equals(other.emailId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consumer [emailId=" + emailId + ", password=" + password + ", mobileNumber=" + mobileNumber + ", birthDate=" + birthDate + ", fullName=" + fullName + ", emailVerified=" + emailVerified + ", phoneVerified=" + phoneVerified + "]";
	}

	public byte[] getIdFront() {
		return idFront;
	}

	public void setIdFront(byte[] idFront) {
		this.idFront = idFront;
	}

	public byte[] getIdBack() {
		return idBack;
	}

	public void setIdBack(byte[] idBack) {
		this.idBack = idBack;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getCreditCardJSON() {
		return creditCardJSON;
	}

	public void setCreditCardJSON(String creditCardJSON) {
		this.creditCardJSON = creditCardJSON;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

}
