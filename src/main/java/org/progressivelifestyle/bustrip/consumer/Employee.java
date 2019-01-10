package org.progressivelifestyle.bustrip.consumer;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@PrimaryKeyJoinColumn(name="ID")
public class Employee extends Consumer {
	@JsonIgnore
	@OneToMany(mappedBy = "pk.employee", fetch = FetchType.LAZY)
	private Set<EmployeeAssignment> assignments;
	private int verificationCode;
	public Employee() {}
	public Employee(String emailId, String password, String mobileNumber, Date birthDate, String fullName, boolean emailVerified, boolean phoneVerified, byte[] idFront, byte[] idBack, byte[] photo, String creditCardJSON) {
		super(emailId, password, mobileNumber, birthDate, fullName, emailVerified, phoneVerified, idFront, idBack, photo, creditCardJSON);
		this.role = Role.EMPLOYEE;
	}
	public Set<EmployeeAssignment> getAssignments() {
		return assignments;
	}
	public void setAssignments(Set<EmployeeAssignment> assignments) {
		this.assignments = assignments;
	}
	public int getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(int verificationCode) {
		this.verificationCode = verificationCode;
	}	
	
}