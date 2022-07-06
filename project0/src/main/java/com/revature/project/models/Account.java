package com.revature.project.models;

import java.util.Objects;

public class Account {
	private int id;
	private String name;
	private String description;
	private double balance;
	private Beneficiary beneficiary;

	public Account() {
		super();
		this.id = 0;
		this.name = "";
		this.description = "";
		this.balance = 0.0;
		this.beneficiary = new Beneficiary();
	}

	public Account(String name, String description, double balance, Beneficiary beneficiary) {
		super();
		this.id = 0;
		this.name = "";
		this.description = "";
		this.balance = 0.0;
		this.beneficiary = new Beneficiary();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Beneficiary getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, description, id, name, beneficiary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(description, other.description)
				&& balance == other.balance && Objects.equals(beneficiary, other.beneficiary);
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", balance=" + balance + ", beneficiary=" + beneficiary + ", description="
				+ description + "]";
	}
}
