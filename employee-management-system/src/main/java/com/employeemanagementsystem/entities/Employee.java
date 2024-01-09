package com.employeemanagementsystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstName;
	private String secondName;
	private String email;
	private String phoneNo;
	private String address;
	private String gender;
	private String joinDate;
	private String department;
	private String position;
	private String salary;
	private String supervisor;
	private String project;
	private String education;
	private String status;
	private long addedByAdminId;
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", email=" + email
				+ ", phoneNo=" + phoneNo + ", address=" + address + ", gender=" + gender + ", joinDate=" + joinDate
				+ ", department=" + department + ", position=" + position + ", salary=" + salary + ", supervisor="
				+ supervisor + ", project=" + project + ", education=" + education + ", status=" + status
				+ ", addedByAdminId=" + addedByAdminId + "]";
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(long id, String firstName, String secondName, String email, String phoneNo, String address,
			String gender, String joinDate, String department, String position, String salary, String supervisor,
			String project, String education, String status, long addedByAdminId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.secondName = secondName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.gender = gender;
		this.joinDate = joinDate;
		this.department = department;
		this.position = position;
		this.salary = salary;
		this.supervisor = supervisor;
		this.project = project;
		this.education = education;
		this.status = status;
		this.addedByAdminId = addedByAdminId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAddedByAdminId() {
		return addedByAdminId;
	}

	public void setAddedByAdminId(long addedByAdminId) {
		this.addedByAdminId = addedByAdminId;
	} 
	
}
